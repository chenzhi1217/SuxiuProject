package com.suxiunet.repair.plugin

import rx.Observable
import rx.subjects.PublishSubject
import rx.subjects.SerializedSubject

/**
 * author : chenzhi
 * time   : 2018/02/02
 * desc   : 
 */
object RxBus {
    private val _bus = SerializedSubject(PublishSubject.create<Any>())

    /**
     * 发送数据的方法
     */
    fun post(o: Any) {
        if (_bus.hasObservers()) {
            _bus.onNext(o)
        }
    }

    fun hasObservers(): Boolean {
        return _bus.hasObservers()
    }

    fun <T> toObservable(eventType: Class<T>): Observable<T> {
        return _bus.ofType(eventType)
    }
}