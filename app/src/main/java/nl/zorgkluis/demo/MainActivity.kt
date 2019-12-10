package nl.zorgkluis.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.layout.Column
import androidx.ui.layout.ExpandedHeight
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class MainActivity : AppCompatActivity() {

    // Enums are implicitly @Serializable
    enum class TintEnum { LIGHT, DARK }

    @Serializable
    data class Data(
        val a: String,
        val b: List<Int>,
        val c: Map<String, TintEnum>
    )

    val data = Data("Str", listOf(1, 2), mapOf("lt" to TintEnum.LIGHT, "dk" to TintEnum.DARK))

    private val json by lazy {
        Json(JsonConfiguration.Stable)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val jsonText = json.stringify(Data.serializer(), data)
        println(jsonText)

        setContent {
            MaterialTheme {
                Column(modifier = ExpandedHeight) {
                    Column(modifier = Flexible(1f)) {
                        Greeting("Android")
                    }

                    Button(onClick = { println("test") }) {
                        Text("Call me")
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Greeting("Android")
    }
}
