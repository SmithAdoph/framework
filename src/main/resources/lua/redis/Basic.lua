function delKey(key, val)
    if redis.call("get", key) == val then
        return redis.call("del", key)
    else
        return 0
    end
end

function max1()
    print('max1')
end

function max2()
    return 'max2'
end

function max3(num)
    return 'max2' .. num
end

--function max3()
--    return 'max2' .. KEYS[1]
--end