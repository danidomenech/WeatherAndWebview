package com.example.weathertest.common.mapper

interface Mapper<in R, out D> {
    fun mapFromOtherModel(otherModel: R): D
    fun mapFromOtherModelList(otherList: List<R>): List<D>
}