package com.example.weathertest.common.mapper

abstract class AbstractMapper<in R, out D> : Mapper<R, D> {
    override fun mapFromOtherModelList(remoteList: List<R>): List<D> {
        return remoteList.map { mapFromOtherModel(it) }
    }
}