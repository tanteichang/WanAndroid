package com.tantei.wanandroid.utils

import android.util.Log

interface ILog {
    fun v(message: String)
    fun v(tag: String, message: String)
    fun d(message: String)
    fun d(tag: String,message: String)
    fun i(message: String)
    fun i(tag: String, message: String)
    fun w(message: String)
    fun w(tag: String, message: String)
    fun e(message: String)
    fun e(tag: String, message: String)
}

private data class ClassInfo(
    val fileName: String,
    val className: String,
    val methodName: String,
    val lineNumber: Int
)

object LLog : ILog {

    private fun getClassInfo(sElement: Array<StackTraceElement>): ClassInfo {
        val className =  sElement[1].className.split("$")[0]
        val methodName = sElement[1].className.split("$")[1]
        return ClassInfo(sElement[1].fileName, className, methodName, sElement[1].lineNumber)
    }

    private var isDebuggable: Boolean = false
    fun setDebuggable(value: Boolean) {
        isDebuggable = value
    }

    private fun createLog(message: String, classInfo: ClassInfo): String {
        val buffer = StringBuffer()
        buffer.append("===[class: ${classInfo.className}]===[method: ${classInfo.methodName}]===[line: ${classInfo.lineNumber}]===\n")
        buffer.append(message + "\n")
        buffer.append("===[end]===\n")
        return buffer.toString()
    }
    private fun log(classInfo: ClassInfo, priority: Int, tag: String, message: String) {
        if (!isDebuggable) { return }
        Log.println(priority, tag, createLog(message, classInfo))
    }

    override fun v(message: String) {
        val classInfo = getClassInfo(Throwable().stackTrace)
        log(classInfo, Log.VERBOSE, classInfo.fileName, message)
    }
    override fun v(tag: String, message: String) {
        val classInfo = getClassInfo(Throwable().stackTrace)
        log(classInfo, Log.VERBOSE, tag, message)
    }
    override fun d(message: String) {
        val classInfo = getClassInfo(Throwable().stackTrace)
        log(classInfo, Log.DEBUG, "", message)
    }
    override fun d(tag: String, message: String) {
        val classInfo = getClassInfo(Throwable().stackTrace)
        log(classInfo, Log.DEBUG, tag, message)
    }
    override fun i(message: String) {
        val classInfo = getClassInfo(Throwable().stackTrace)
        log(classInfo, Log.INFO, "", message)
    }
    override fun i(tag: String, message: String) {
        val classInfo = getClassInfo(Throwable().stackTrace)
        log(classInfo, Log.INFO, tag, message)
    }
    override fun w(message: String) {
        val classInfo = getClassInfo(Throwable().stackTrace)
        log(classInfo, Log.WARN, "", message)
    }
    override fun w(tag: String, message: String) {
        val classInfo = getClassInfo(Throwable().stackTrace)
        log(classInfo, Log.WARN, tag, message)
    }
    override fun e(message: String) {
        val classInfo = getClassInfo(Throwable().stackTrace)
        log(classInfo, Log.ERROR, "", message)
    }
    override fun e(tag: String, message: String) {
        val classInfo = getClassInfo(Throwable().stackTrace)
        log(classInfo, Log.ERROR,  tag, message)
    }
}