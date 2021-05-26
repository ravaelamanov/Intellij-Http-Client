package services.persistence

import com.intellij.openapi.components.PersistentStateComponent

abstract class AbstractPersistenceService<S> : PersistentStateComponent<S> {
    abstract var objState: S

    override fun getState(): S? {
        return objState
    }

    override fun loadState(state: S) {
        objState = state
    }
}
