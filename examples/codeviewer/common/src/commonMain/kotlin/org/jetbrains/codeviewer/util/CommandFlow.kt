package org.jetbrains.codeviewer.util

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.AbstractFlow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.receiveAsFlow

@OptIn(FlowPreview::class)
class CommandFlow<T> : AbstractFlow<T>() {
    private val channel = Channel<T>(Channel.UNLIMITED)

    fun emit(value: T) {
        channel.offer(value)
    }

    override suspend fun collectSafely(collector: FlowCollector<T>) {
        collector.emitAll(channel.receiveAsFlow())
    }
}