package org.jetbrains.codeviewer

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import org.jetbrains.codeviewer.platform.PlatformTheme
import org.jetbrains.codeviewer.platform.toProjectFile
import org.jetbrains.codeviewer.ui.common.Settings
import org.jetbrains.codeviewer.ui.editor.Editor
import org.jetbrains.codeviewer.ui.editor.EditorView
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.nio.file.Files
import java.nio.file.Path
import kotlinx.coroutines.*

class SearchState(root: File, var line: Long = 0) {
    private var files = start(root)
    private fun start(root: File) = Files.walk(root.toPath()).filter(Files::isRegularFile).iterator()
    private fun findString(file: Path, marker: String): Long {
        var lineNumber = 0L
        var foundLine = 0L
        BufferedReader(FileReader(file.toFile())).use { br ->
            var lineString: String?
            while (br.readLine().also { lineString = it } != null) {
                lineNumber++
                if (lineString!!.indexOf(marker) >= 0) {
                    foundLine = lineNumber
                    break
                }
            }
        }
        return foundLine
    }
    private var current = ""

    @Synchronized
    fun reset(root: File) {
        files = start(root)
    }


    private val ignoredExtensions = arrayOf(".class", ".zip", ".jar", ".apk")
    @Synchronized
    fun next(marker: String): String {
        while (files.hasNext()) {
            val file = try {
                files.next()
            } catch (t: Throwable) {
                current = ""
                break
            }
            if (ignoredExtensions.any { file.toString().endsWith(it) }) continue
            val line = findString(file, marker)
            if (line == 0L) continue
            current = file.toAbsolutePath().toString()
            this.line = line
            break
        }
        if (!files.hasNext()) current = ""
        return current
    }
}

fun main() {
    Window(
            title = "Update version",
            size = IntSize(1280, 768)
        ) {
        var newCompose by remember { mutableStateOf("") }
        var repo by remember { mutableStateOf(File("../..").canonicalPath) }
        var updating by remember { mutableStateOf(false) }
        var searching by remember { mutableStateOf(false) }
        val searchState = SearchState(File(repo))
        val composeMarker = "__LATEST_COMPOSE_RELEASE_VERSION__"
        var currentFile by remember { mutableStateOf("") }

        fun onNext() {
            CoroutineScope(Dispatchers.IO).launch {
                searching = true
                currentFile = searchState.next(composeMarker)
                if (currentFile.isEmpty()) {
                    updating = false
                    searchState.reset(File(repo))
                }
                searching = false
            }
        }

        MaterialTheme {
            PlatformTheme {
                Column {
                    TextField(
                            value = repo,
                            modifier = Modifier.padding(8.dp),
                            label = { Text("Repo path") },
                            onValueChange = { repo = it }
                    )
                    TextField(
                            value = newCompose,
                            modifier = Modifier.padding(8.dp),
                            label = { Text("New Compose version") },
                            onValueChange = {
                                newCompose = it
                            }
                    )
                    Button(onClick = {
                        updating = !updating
                        if (updating) {
                            onNext()
                        } else {
                            searchState.reset(File(repo))
                            currentFile = ""
                            searching = false
                        }
                    }) {
                        Text(if (updating) "Stop update" else "Start update")
                    }
                    if (updating) {
                        Row {
                            Text("Current: $currentFile", modifier = Modifier.height(30.dp))
                            if (searching)
                                CircularProgressIndicator(modifier = Modifier.height(30.dp))
                        }
                        if (currentFile.isNotEmpty()) {
                            UpdateView(currentFile, searchState.line, composeMarker, newCompose, ::onNext)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UpdateView(current: String, line: Long, marker: String, newValue: String, onNext: () -> Unit) {
    val editor = Editor(File(current).toProjectFile())
    Column {
        Row {
            Button(
                onClick = {
                    println("Update")
                    onNext()
                }
            ) {
                Text("Replace")
            }
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = {
                    println("Skip")
                    onNext()
                }
            ) {
                Text("Skip")
            }
        }
        TextField(
            value = "XXXXX",
            onValueChange = { }
        )
        EditorView(editor, Settings())
    }
}
