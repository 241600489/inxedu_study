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
--  0 选课成功,正常,多值
-- -1 多选,无效果,正常,单值
   -2 这次完成后满人了,删除课程成功,正常,多值
   -3 这次完成后满人了,删除课程失败 业务正常,服务器异常,多值
   -4 没这个课程但是被访问了,删除课程成功(为为多数情况下减少一次先查询再减数的优化做的做的补偿),业务异常,服务器正常,单值
   -5 没这个课程但是被访问了,删除课程失败,业务异常,服务器异常,单值

    db0 课程的剩余学生数量
    db1 学生课程id对对应学生id的集合
    db2 学生id对应课程的集合


 ]]
local sId = ARGV[1]
local tId = ARGV[2]
local call = redis.pcall
call('SELECT', 1)
if (call('SISMEMBER', sId, tId) == 1) then
    call('SELECT', 0)
    local endRes = call('INCR',tId, 1)
    call('SELECT', 1)
    local studentRes = call('SMOVE', sId, tId)
    return { endRes, studentRes }
else
    return {}
end







