package com.github.ravaelamanov.intellijhttpclient.services

import com.github.ravaelamanov.intellijhttpclient.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
