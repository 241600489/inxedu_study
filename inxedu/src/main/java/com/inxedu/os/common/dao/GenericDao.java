//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.inxedu.os.common.dao;

import com.inxedu.os.common.entity.PageEntity;
import java.util.List;

public interface GenericDao {
    Long insert(String var1, Object var2);

    Long delete(String var1, Object var2);

    Long update(String var1, Object var2);

    <T> T selectOne(String var1, Object var2);

    <T> List<T> selectList(String var1, Object var2);

    <T> List<T> queryForListPage(String var1, Object var2, PageEntity var3);
}
