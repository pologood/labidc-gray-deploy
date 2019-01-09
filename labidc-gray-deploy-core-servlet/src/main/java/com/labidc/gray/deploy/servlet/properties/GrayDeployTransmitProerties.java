package com.labidc.gray.deploy.servlet.properties;

import com.labidc.gray.deploy.servlet.transmit.HeadTransmitAttributeObjectTransform;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/***
 *
 * @author xiongchuang
 * @date 2018-01-15
 */
@ConfigurationProperties("spring.gray.deploy.transmit")
@Component
@Data
public class GrayDeployTransmitProerties {
    /**
     * 向后续服务传递当前服务请求中的header
     * <注意> 如果请求中有相同名称的header 将不会传递 ( 使用feign中带有的header值 )
     */
    private List<String> headers;

    /**
     * 向后续服务传递当前服务请求中的attribute
     * <注意> 默认传递 String 和 基本类型    对象需要实现接口 {@link HeadTransmitAttributeObjectTransform} 并 注册为bean
     * <注意> attribute 会传递到 后续服务请求中的 header 里
     * <注意> 如果请求中有相同名称的header 将不会传递 ( 使用feign中带有的header值 )
     * <注意> 如果 attributes和headers指定的相同名称 优先取header中值 header 无值时才取 attribute中值  ifnull(header,attribute)
     */
    private List<String> attributes;
}
