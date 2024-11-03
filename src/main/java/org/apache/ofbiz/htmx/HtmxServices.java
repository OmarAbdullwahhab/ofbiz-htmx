package org.apache.ofbiz.htmx;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.util.EntityQuery;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.ServiceUtil;

import java.time.LocalDateTime;

public class HtmxServices {

    private static final String MODULE = HtmxServices.class.getName();

    public static Map<String, Object> sayDate(DispatchContext dispatchContext, Map<String, Object> context) {

        var result = ServiceUtil.returnSuccess();
        result.put("currentDate", LocalDateTime.now().toString());
        return result;

    }

    public static Map<String, Object> getCountriesList(DispatchContext dispatchContext, Map<String, Object> context) {
        var result = ServiceUtil.returnSuccess();
        Delegator delegator = dispatchContext.getDelegator();
        List<SelectOption> options = new ArrayList<>();
        try {
            var found = EntityQuery.use(delegator).from("Geo").where("geoTypeId", "COUNTRY").cache().queryList();
            if (found != null) {
                for (GenericValue geo : found) {
                    options.add(new SelectOption(geo.getString("geoId"), geo.getString("geoName")));
                }
            }
        } catch (GenericEntityException e) {
            Debug.logError(e, e.getMessage(), MODULE);
        }

        result.put("options", options);
        return result;

    }

    public static Map<String, Object> getRegionsList(DispatchContext dispatchContext, Map<String, Object> context) {
        var result = ServiceUtil.returnSuccess();
        Delegator delegator = dispatchContext.getDelegator();
        List<SelectOption> options = new ArrayList<>();
        String countryId = context.get("countryId").toString();
        try {
            var found = EntityQuery.use(delegator).from("GeoAssocAndGeoTo")
                    .where("geoAssocTypeId", "REGIONS", "geoIdFrom", countryId).cache().queryList();
            if (found != null) {
                for (GenericValue geo : found) {
                    options.add(new SelectOption(geo.getString("geoId"), geo.getString("geoName")));
                }
            }
        } catch (GenericEntityException e) {
            Debug.logError(e, e.getMessage(), MODULE);
        }

        result.put("options", options);
        return result;

    }
}
