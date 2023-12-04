package com.example.arendapro.dao;

import com.example.arendapro.config.redis.ImmovablesCache;
import com.example.arendapro.entity.Immovables;
import com.example.arendapro.mapper.ImmovablesCacheMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Repository
public class ImmovablesCacheDao {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;


    public void saveAll(List<ImmovablesCache> immovables){
        log.info(immovables.get(0).getId().toString());
        for (ImmovablesCache i: immovables){
            template.opsForHash().put("ImmovablesCache", i.getId(), i);
        }
    }
    public List<Immovables> findAll() {
        List<ImmovablesCache> immovablesCache = template.opsForHash().values("ImmovablesCache");
        List<Immovables> immovables = new ArrayList<>();
//        for (ImmovablesCache i: immovablesCache){
//            immovables.add(mapper.toEntity(i));
//        }
        return immovables;
    }

}
