package com.example.arendapro.dao;

import com.example.arendapro.config.redis.ImmovablesCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;


@Slf4j
@Repository
public class ImmovablesCacheDao {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;


    public void save(ImmovablesCache i){
            template.opsForHash().put("ImmovablesCache", i.getId(), i);

    }
    public List<ImmovablesCache> findAll() {
        List<ImmovablesCache> immovablesCache = template.opsForHash().values("ImmovablesCache");
        return immovablesCache;
    }

    public void deleteCache(ImmovablesCache i) {
        template.opsForHash().delete("ImmovablesCache", i.getId(), i);
    }

    public void deleteAllCache() {
        template.delete("ImmovablesCache");
    }
}
