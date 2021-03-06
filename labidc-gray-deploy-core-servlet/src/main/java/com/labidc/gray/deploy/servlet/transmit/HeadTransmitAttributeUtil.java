package com.labidc.gray.deploy.servlet.transmit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 *
 * @author xiongchuang
 * @date 2018-01-15
 */
class HeadTransmitAttributeUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HeadTransmitAttributeUtil.class);
    private static final String TYPE = "TYPE";

    private HeadTransmitAttributeUtil() {
    }

    static String objectToString(String headerName, Object headerValueObject, HeadTransmitObjectTransform feignHeadTransmitAttributeObjectTransform) {
        if (headerValueObject == null) {
            return null;
        }

        String headerValue = null;
        if (headerValueObject instanceof String) {
            headerValue = (String) headerValueObject;
        } else {
            Class<?> aClass = headerValueObject.getClass();
            if (aClass.isPrimitive() || isWrapClass(aClass)) {
                headerValue = headerValueObject.toString();
            } else if (feignHeadTransmitAttributeObjectTransform != null) {
                try {
                    headerValue = feignHeadTransmitAttributeObjectTransform.transform(headerName, headerValueObject);
                } catch (Exception e) {
                    LOGGER.error("Attribute  Object To String  err : {}", e.getMessage());
                }
            }
        }

        return headerValue;
    }

    private static boolean isWrapClass(Class clz) {
        try {
            return ((Class) clz.getField(TYPE).get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

}
