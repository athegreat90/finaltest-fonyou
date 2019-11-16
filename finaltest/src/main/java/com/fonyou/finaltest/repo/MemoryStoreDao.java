package com.fonyou.finaltest.repo;

import com.fonyou.finaltest.dto.EmployeeDto;
import com.fonyou.root.utils.lib.interfaces.RedisConnectionFactoryInterface;
import com.fonyou.root.utils.lib.interfaces.RedisThreadDaoInterface;
import com.fonyou.root.utils.lib.interfaces.WsResponseDtoInterface;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
@Scope(value = "prototype")
public class MemoryStoreDao implements RedisThreadDaoInterface
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MemoryStoreDao.class);

    @Autowired
    Gson gson;

    @Autowired
    private MemoryConnectionFactory memoryConnectionFactory;

    private Jedis jedisInstance;

    public EmployeeDto getEmployee(String id)
    {
        try
        {
            return this.get(EmployeeDto.class, id);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public Boolean saveEmployee(EmployeeDto body)
    {
        try
        {
            return this.set(body.getId(), gson.toJson(body), 3600);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public Boolean deleteEmployee(String id)
    {
        try
        {
            return this.del(id);
        }
        catch (Exception e)
        {
            throw e;
        }
    }


    @Override
    public String getSmsNotifyQueueKey()
    {
        return "";
    }

    @Override
    public RedisConnectionFactoryInterface getRedisDaoFactory()
    {
        return memoryConnectionFactory;
    }

    @Override
    public Jedis getRedisInstance()
    {
        return jedisInstance;
    }

    @Override
    public void setRedisInstance(Jedis connection)
    {
        this.jedisInstance = connection;
    }

    @Override
    public void writeWsResponse(WsResponseDtoInterface wsResponseDto)
    {
        LOGGER.trace("overriding");
    }

    @Override
    public Gson getGson()
    {
        return gson;
    }
}
