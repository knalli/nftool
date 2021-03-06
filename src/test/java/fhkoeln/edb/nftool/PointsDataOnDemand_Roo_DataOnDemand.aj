// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package fhkoeln.edb.nftool;

import fhkoeln.edb.nftool.Points;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;

privileged aspect PointsDataOnDemand_Roo_DataOnDemand {
    
    declare @type: PointsDataOnDemand: @Component;
    
    private Random PointsDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<Points> PointsDataOnDemand.data;
    
    public Points PointsDataOnDemand.getNewTransientPoints(int index) {
        fhkoeln.edb.nftool.Points obj = new fhkoeln.edb.nftool.Points();
        obj.setUsername("username_" + index);
        obj.setGameDate(new java.util.GregorianCalendar(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR), java.util.Calendar.getInstance().get(java.util.Calendar.MONTH), java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH), java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY), java.util.Calendar.getInstance().get(java.util.Calendar.MINUTE), java.util.Calendar.getInstance().get(java.util.Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime());
        java.lang.Long points = new Integer(index).longValue();
        if (points < 0L) {
            points = 0L;
        }
        obj.setPoints(points);
        java.lang.String appKey = "appKey_" + index;
        if (appKey.length() > 10) {
            appKey  = appKey.substring(0, 10);
        }
        obj.setAppKey(appKey);
        return obj;
    }
    
    public Points PointsDataOnDemand.getSpecificPoints(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        Points obj = data.get(index);
        return Points.findPoints(obj.getId());
    }
    
    public Points PointsDataOnDemand.getRandomPoints() {
        init();
        Points obj = data.get(rnd.nextInt(data.size()));
        return Points.findPoints(obj.getId());
    }
    
    public boolean PointsDataOnDemand.modifyPoints(Points obj) {
        return false;
    }
    
    public void PointsDataOnDemand.init() {
        data = fhkoeln.edb.nftool.Points.findPointsEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'Points' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new java.util.ArrayList<fhkoeln.edb.nftool.Points>();
        for (int i = 0; i < 10; i++) {
            fhkoeln.edb.nftool.Points obj = getNewTransientPoints(i);
            obj.persist();
            obj.flush();
            data.add(obj);
        }
    }
    
}
