package com.xinyi.xinfo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xinyi.xinfo.bean.MapPoint;
import com.xinyi.xinfo.bean.PageParams;

public class OpenLayersUtil {

    private static final double EARTH_RADIUS = 6378137L;

    
    public static void main(String[] args) {
    	System.out.println(getDistance(113.92149,22.523688,113.946585,22.523688));
	}
    
	public static double rad(double n) {
    	
    	 return n * Math.PI / 180.0; 
    }
	/**
     * 通过经纬度获取距离(单位：米)
     *
     * @param lat1 维度 y
     * @param lng1 经度 x
     * @param lat2
     * @param lng2
     * @return
     */
    public static double getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        return s;
    }
 
    /**
     * 判断一个点是否在圆形区域内
     */
    public static boolean isInCircle(double lng1, double lat1, double lng2, double lat2, String radius) {
        double distance = getDistance(lat1, lng1, lat2, lng2)*10;
        double r = Double.parseDouble(radius)*10;
        if (distance > r) {    	
            return false;
        } else {
            return true;
        }
    }
    /**
     * 判断一个点是否在矩形区域内
     */
    public static boolean isInRectangle(Double xMin, Double xMax, Double yMin, Double yMax,Double x, Double y) {
    	boolean r = false;
    	if(y==null||x==null) {
    		return r;
    	}
    	if(xMin<=x&&x<=xMax&&yMin<=y&&y<=yMax) {
    		r = true;
    	}
    	
		return r;
    }
    /**
     * 过滤不需要的点位
     * 
     * @param pointList
     * @param pageParams
     * @return
     */
    private List<MapPoint> fitlerPolygonResult(List<MapPoint> pointList, PageParams pageParams)
    {
        List<MapPoint> result = new ArrayList<MapPoint>();
        // 多边形需要去掉查询框选外的点
        String type = (String) pageParams.getQueryParams().get("type");
        String pointers = (String) pageParams.getQueryParams().get("position");
        if (!StringUtils.isEmpty(type) && type.equals("Polygon") && !StringUtils.isEmpty(pointers))
        {
            List<Map<String, Object>> pointerList = this.formatPointers(pointers);
            for (MapPoint mapPoint : pointList)
            {
                String x = mapPoint.getX();
                String y = mapPoint.getY();
                String centerPoints = mapPoint.getCentPoints();//小区城中村中心点位
                if ((!StringUtils.isEmpty(x) && !StringUtils.isEmpty(y)) || !StringUtils.isEmpty(centerPoints))
                {
                    try
                    {
                        double lon = 0;// x
                        double lat = 0;// y
                        if(!StringUtils.isEmpty(centerPoints)){
                        	String centerPointsArray[] = centerPoints.split(",");
                        	 lon = Double.parseDouble(centerPointsArray[0]);// x
                             lat = Double.parseDouble(centerPointsArray[1]);// y
                        }else{
                        	lon = Double.parseDouble(x);// x
                            lat = Double.parseDouble(y);// y
                        }
                        boolean flag = this.pointInPolygon(lon, lat, pointerList);
                        if (flag)
                        {
                            result.add(mapPoint);
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        else
        {
            result.addAll(pointList);
        }
        return result;
    }
    /**
     * 过滤不需要的点位
     * 
     * @param pointList
     * @param pageParams
     * @return
     */
    private List<MapPoint> fitlerPolygonResult(List<MapPoint> pointList, List<Map<String, Object>> pointerList)
    {
    	List<MapPoint> result = new ArrayList<MapPoint>();
    	// 多边形需要去掉查询框选外的点
    	if (pointerList.size()>0)
    	{
    		for (MapPoint mapPoint : pointList)
    		{
    			String x = mapPoint.getX();
    			String y = mapPoint.getY();
    			String centerPoints = mapPoint.getCentPoints();//小区城中村中心点位
    			if ((!StringUtils.isEmpty(x) && !StringUtils.isEmpty(y)) || !StringUtils.isEmpty(centerPoints))
    			{
    				try
    				{
    					double lon = 0;// x
    					double lat = 0;// y
    					if(!StringUtils.isEmpty(centerPoints)){
    						String centerPointsArray[] = centerPoints.split(",");
    						lon = Double.parseDouble(centerPointsArray[0]);// x
    						lat = Double.parseDouble(centerPointsArray[1]);// y
    					}else{
    						lon = Double.parseDouble(x);// x
    						lat = Double.parseDouble(y);// y
    					}
    					boolean flag = this.pointInPolygon(lon, lat, pointerList);
    					if (flag)
    					{
    						result.add(mapPoint);
    					}
    				}
    				catch (Exception e)
    				{
    					e.printStackTrace();
    				}
    			}
    		}
    	}
    	else
    	{
    		result.addAll(pointList);
    	}
    	return result;
    }

    /**
     * 判断点位是否存在多边形中
     * （判断点位向一个反向平行延伸如果和多边形相交次数是单数或者平行则在区域内）
     * @param lon
     * @param lat
     * @param pointerList
     * @return
     */
    public static boolean pointInPolygon(double lon, double lat, List<Map<String, Object>> pointerList)
    {
        boolean flag = false;
        int pSize = pointerList.size();
        for (int i = 0, j = pointerList.size() - 1; i < pSize; j = i++)
        {
            double platMin = Double.valueOf((String) pointerList.get(i).get("lat")) ;
            double platMax = Double.valueOf((String) pointerList.get(j).get("lat")) ;
            double plonMin = Double.valueOf((String) pointerList.get(i).get("lon")) ;
            double plonMax = Double.valueOf((String) pointerList.get(j).get("lon")) ;
            if (((platMin > lat) != (platMax > lat)) && (lon < (plonMax - plonMin) * (lat - platMin) / (platMax - platMin) + plonMin))
            {
                flag = !flag;
            }
        }
        return flag;
    }

    private List<Map<String, Object>> formatPointers(String pointers)
    {
        List<Map<String, Object>> pointerList = new ArrayList<Map<String, Object>>();
        String[] pointerArr = pointers.split(";");
        Map<String, Object> pointMap = null;
        for (String point : pointerArr)
        {
            pointMap = new HashMap<String, Object>();
            String[] pointXy = point.split(",");
            try
            {
                pointMap.put("lon", Double.parseDouble(pointXy[0]));
                pointMap.put("lat", Double.parseDouble(pointXy[1]));
                pointerList.add(pointMap);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return pointerList;
    }

    /**
     * 根据多边形获取最大坐标和最小坐标组成的矩形
     * @param pointerList
     */
    public static Map<String, Object> getRectangleBYpolygon(List<Map<String, Object>> pointerList) {
    	
    	Double xMax = null;
    	Double yMax = null;
    	Double xMin = null;
    	Double yMin = null;
    	
    	for (Map<String, Object> map : pointerList) {
			if(xMax==null||xMax<Double.valueOf(map.get("lon").toString())) {
				xMax = Double.valueOf(map.get("lon").toString());
			}
			if(yMax==null||yMax<Double.valueOf(map.get("lat").toString())) {
				yMax = Double.valueOf(map.get("lat").toString());
			}
			if(xMin==null||xMin>Double.valueOf(map.get("lon").toString())) {
				xMin = Double.valueOf(map.get("lon").toString());
			}
			if(yMin==null||yMin>Double.valueOf(map.get("lat").toString())) {
				yMin = Double.valueOf(map.get("lat").toString());
			}
		}
    	Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("xMin", xMin);
		queryParams.put("xMax", xMax);
		queryParams.put("yMin", yMin);
		queryParams.put("yMax", yMax);
		
		return queryParams;
    }

}
