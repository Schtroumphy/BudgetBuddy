package com.jeanloth.mobile.android.budgetbuddy.core

interface Mapper<T, I> {

    fun from(entity : T) : I

    fun to(pojo: I) : T
}