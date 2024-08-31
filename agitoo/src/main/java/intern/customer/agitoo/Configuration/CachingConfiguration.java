package intern.customer.agitoo.Configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@EnableCaching
public class CachingConfiguration {

    public CacheManager cacheManager () {
        SimpleCacheManager cashList = new SimpleCacheManager ();
        cashList.setCaches (Arrays.asList (
                new ConcurrentMapCache ("customer"),
                new ConcurrentMapCache ("customer-address"),
                new ConcurrentMapCache ("customer-address-city"),
                new ConcurrentMapCache ("customer-address-country"),
                new ConcurrentMapCache ("customer-claim"),
                new ConcurrentMapCache ("customer-contact"),
                new ConcurrentMapCache ("customer-debit-cart"),
                new ConcurrentMapCache ("customer-payment"),
                new ConcurrentMapCache ("customer-policy"),
                new ConcurrentMapCache ("customer-poicy-renewal"),
                new ConcurrentMapCache ("customer-registration"),
                new ConcurrentMapCache ("person"),
                new ConcurrentMapCache ("person-activity"),
                new ConcurrentMapCache ("person-feedback"),
                new ConcurrentMapCache ("person-job-life"),
                new ConcurrentMapCache ("person-support-ticket"),
                new ConcurrentMapCache ("company"),
                new ConcurrentMapCache ("company-branch"),
                new ConcurrentMapCache ("company-financial")
        ));

        return cashList;
    }
}
