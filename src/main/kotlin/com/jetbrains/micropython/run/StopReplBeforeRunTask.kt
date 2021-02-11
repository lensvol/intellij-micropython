package com.jetbrains.micropython.run

import com.intellij.execution.BeforeRunTask
import com.intellij.execution.BeforeRunTaskProvider
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.util.Key
import com.jetbrains.micropython.settings.MicroPythonFacetType

class StopReplBeforeRunTask : BeforeRunTask<StopReplBeforeRunTask>(StopReplBeforeRunTaskProvider.ID)

class StopReplBeforeRunTaskProvider : BeforeRunTaskProvider<StopReplBeforeRunTask>() {
    companion object {
        val ID = Key.create<StopReplBeforeRunTask>("MicroPython.StopREPL.Before.Run")
    }

    override fun getId() = ID

    override fun getIcon() = MicroPythonFacetType.LOGO

    override fun getName() = "Stop MicroPython REPL"

    override fun createTask(runConfiguration: RunConfiguration) = StopReplBeforeRunTask()

    override fun executeTask(
        context: DataContext,
        configuration: RunConfiguration,
        environment: ExecutionEnvironment,
        task: StopReplBeforeRunTask
    ): Boolean {
        DeviceCommsManager.getInstance(environment.project).stopREPL()
        return true
    }

}