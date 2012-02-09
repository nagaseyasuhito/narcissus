package com.github.nagaseyasuhito.narcissus.dao;

import com.github.nagaseyasuhito.narcissus.entity.BaseManagedEntity;

public abstract class BaseManagedDao<T extends BaseManagedEntity> extends BaseDao<Long, T> {
}
