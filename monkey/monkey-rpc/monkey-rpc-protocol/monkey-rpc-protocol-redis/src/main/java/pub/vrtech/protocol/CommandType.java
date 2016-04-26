/*
 * Copyright 2016  VRTECH.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pub.vrtech.protocol;

/**
 *
 * Function description： 
 * REDIS commands
 * 
 * @author houge
 */
public enum CommandType {
    
    
    APPEND(0,"append"),
    
    BITCOUNT(1,"bitcount"),
    
    BITTOP(2,"bittop"),
    
    DECR(3,"decr"),
    
    DECRBY(4,"decrby"),
    
    GET(5,"get"),
    
    GETBIT(6,"getbit"),
    
    GETRANGE(7,"getrange"),
    
    GETSET(8,"getset"),
    
    INCR(9,"incr"),
    
    INCRBY(10,"incrby"),
    
    INCRBYFLOAT(11,"incrbyfloat"),
    
    MGET(12,"mget"),
    
    MSET(13,"mset"),
    
    MSETNX(14,"msetnx"),
    
    PSETEX(15,"psetex"),
    
    SET(16,"set"),
    
    SETBIT(17,"setbit"),
    
    SETEX(18,"setex"),
    
    SETNX(19,"setnx"),
    
    SETRANGE(20,"setrange"),
    
    STRLEN(21,"strlen"),
    
    ECHO(22,"echo"),

    PING(23,"ping"),
    
    QUIT(24,"quit"),
    
    SELECT(25,"select"),
    
    BGREWRITEAOF(26,"bgrewriteaof"),
    
    BGSAVE(27,"bgsave"),
    
    CLIENT_KILL(28,"client_kill"),
    
    CLIENT_LIST(29,"client_list"),
    
    CLIENT_GETNAME(30,"client_getname"),
    
    CLIENT_SETNAME(31,"client_setname"),
    
    CONFIG_GET(32,"config_get"),
    
    CONFIG_SET(33,"config_set"),
    
    CONFIG_RESETSTAT(34,"config_resetstat"),
    
    DBSIZE(35,"dbsize"),
    
    DEBUG_OBJECT(36,"debug_object"),
    
    DEBUG_SEGFAULT(37,"debug_segfault"),
    
    FLUSHALL(38,"flushall"),
    
    FLUSHDB(39,"flushdb"),
    
    INFO(40,"info"),
    
    LASTSAVE(41,"lastsave"),
    
    MONITOR(42,"monitor"),
    
    SAVE(43,"save"),
    
    SHUTDOWN(44,"shutdown"),
    
    SLAVEOF(45,"slaveof"),
    
    SLOWLOG(46,"slowlog"),
    
    SYNC(47,"sync"),
    
    TIME(48,"time"),
    
    BLPOP(49,"blpop"),
    
    BRPOP(50,"brpop"),
    
    BRPOPLPUSH(51,"brpoplpush"),
    
    LINDEX(52,"lindex"),
    
    LINSERT(53,"linsert"),
    
    LLEN(54,"llen"),
    
    LPOP(55,"lpop"),
    
    LPUSH(56,"lpush"),
    
    LPUSHX(57,"lpushx"),
    
    LRANGE(58,"lrange"),
    
    LREM(60,"lrem"),
    
    LSET(61,"lset"),
    
    LTRIM(62,"ltrim"),
    
    RPOP(63,"rpop"),
    
    RPOPLPUSH(64,"rpoplpush"),
    
    RPUSH(65,"rpush"),
    
    RPUSHX(66,"rpushx"),
    
    DEL(67,"del"),
    
    DUMP(68,"dump"),
    
    EXISTS(69,"exists"),
    
    EXPIRE(70,"expire"),
    
    EXPIREAT(71,"expireat"),
    
    KEYS(72,"keys"),
    
    MIGRATE(73,"migrate"),
    
    MOVE(74,"move"),
    
    OBJECT(75,"object"),
    
    PERSIST(76,"persist"),
    
    PEXPIRE(77,"pexpire"),
    
    PEXPIREAT(78,"pexpireat"),
    
    PTTL(79,"pttl"),
    
    RANDOMKEY(80,"randomkey"),
    
    RENAME(81,"rename"),
    
    RENAMENX(82,"renamenx"),
    
    RESTORE(83,"restore"),
    
    SORT(84,"sort"),
    
    TTL(85,"ttl"),
    
    TYPE(86,"type"),
    
    UNWATCH(87,"unwatch"),
    
    WATCH(88,"watch"),
    
    EVAL(89,"eval"),
    
    EVALSHA(90,"evalsha"),
    
    SCRIPT_EXISTS(91,"script_exists"),
   
    SCRIPT_FLUSH(92,"script_flush"),

    SCRIPT_KILL(93,"script_kill"),
    
    SCRIPT_LOAD(94,"script_load"),
    
    HDEL(95,"hdel"),
    
    HEXISTS(96,"hexists"),
    
    HGET(97,"hget"),
    
    HGETALL(98,"hgetall"),
    
    HINCRBY(99,"hincrby"),
    
    HINCRBYFLOAT(100,"hincrbyfloat"),
    
    HKEYS(101,"hkeys"),
    
    HLEN(102,"hlen"),
    
    HMGET(103,"hmget"),
    
    HMSET(104,"hmset"),
    
    HSET(105,"hset"),
    
    HSETNX(106,"hsetnx"),
    
    HVALS(107,"hvals"),
    
    PUHLISH(108,"publish"),
    
    SADD(109,"sadd"),
    
    SCARD(110,"scard"),
    
    SDIFF(111,"sdiff"),
    
    SDIFFSTORE(112,"sdiffstore"),
    
    SINTER(113,"sinter"),
    
    SINTERSTORE(114,"sinterstore"),
    
    SISMEMBER(115,"sismember"),
    
    SMEMBERS(116,"smembers"),
    
    SMOVE(117,"smove"),
    
    SPOP(118,"spop"),
    
    SRANDMEMBER(119,"srandmember"),
    
    SREM(120,"srem"),
    
    SUNION(121,"sunion"),
    
    SUNIONSTORE(122,"sunionstore"),
    
    ZADD(123,"zadd"),
    
    ZCARD(124,"zcard"),
    
    ZCOUNT(125,"zcount"),
    
    ZINCRBY(126,"zincrby"),
    
    ZINTERSTORE(127,"zinterstore"),
    
    ZRANGE(128,"zrange"),
    
    ZRANGEBYSCORE(129,"zrangebyscore"),
    
    ZRANK(130,"zrank"),
    
    ZREM(131,"zrem"),
    
    ZREMRANGEBYRANK(132,"zremrangebyrank"),
    
    ZREMRANGEBYSCORE(133,"zremrangebyscore"),
    
    ZREVRANGE(134,"zrevrange"),
    
    ZREVRANGEBYSCORE(135,"zrevrangebyscore"),
    
    ZREVRANK(136,"zrevrank"),
    
    ZSCORE(137,"zscore"),
    
    ZUNIONSTORE(138,"zunionstore"),
    
    ;
    
    private final int id;
    private final String desc;

    private CommandType(final int id, final String desc) {
        this.id = id;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

}
