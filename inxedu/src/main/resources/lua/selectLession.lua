--
-- Created by IntelliJ IDEA.
-- User: 陈俊文
-- Date: 16-6-21
-- Time: 16:58
-- To change this template use File | Settings | File Templates.
--
-- 按机器,我双核i5单线程写入,一个redis,每秒2265事务,所以限流到每秒2000事务
-- id是学生id和课程id生成的唯一值,可以由这个id查询到选到的课
--[[
--  {} 已经选过课了
--  {n} 选课后还剩下n个空位,正常值 0,负值说明前端把请求打进来了,有问题

 ]]
local sId = ARGV[1]
local tId = ARGV[2]
local call = redis.pcall
call('SELECT', 1)
if (call('SISMEMBER', sId, tId) == 0) then
    call('SELECT', 0)
    local endRes = call('DECR', tId)
    call('SELECT', 1)
    if (endRes > -1) then
        call('SADD', sId, tId)
    end
    return { endRes }
else
    return {}
end







