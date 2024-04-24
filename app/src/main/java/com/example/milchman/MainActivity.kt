package com.example.milchman

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.milchman.ui.theme.MilchmanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MilchmanTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val orderList = OrdersList(mutableListOf(
                            Order("Ahmet", 5, 0, "Käse"),
                            Order("Mehmet", 10,3,"Joghurt" ),
                            Order("Ayse", 0, 1, "2 Badem 1 Findik"),
                            Order("Zeynep", 20, 2, "1 Ceviz 1 Yogurt 2 Bal")
                        )
                    )

                    InitTable(orderList)
                }
            }
        }
    }
}


@Composable
fun InitTable(orderList: OrdersList) {
    val orders = orderList.getOrders()

    val maxName = orders.maxOf { it.name.length }
    val maxOtherLength = orders.maxOf { it.other.length }
    val titleLength = "Sonstige".length

    val cellWidth: (Int) -> Dp = { index ->
        val cellWidths = listOf(50.dp, (maxName*20).dp, 70.dp, 70.dp,
            if (maxOtherLength > titleLength) (maxOtherLength*12).dp else 120.dp, 150.dp
        )
        cellWidths.getOrNull(index) ?: 150.dp
    }

    val headerCellTitle: @Composable (Int) -> Unit = { index ->
        val columnTitles = listOf("#", "Name", "Milch", "Eier", "Sonstiges")
        val value = columnTitles.getOrNull(index) ?: ""

        Text(
            text = value,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Black,
            textDecoration = TextDecoration.Underline
        )
    }

    val cellText: @Composable (Int, Order) -> Unit = { index, item ->
        val value = when (index) {
            0 -> (orders.indexOf(item) + 1).toString()
            1 -> item.name
            2 -> item.milk.toString()
            3 -> item.egg.toString()
            4 -> item.other
            else -> ""
        }
        Text(
            text = value,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }

    Table(
        columnCount = 5,
        cellWidth = cellWidth,
        data = orders,
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        headerCellContent = headerCellTitle,
        cellContent = cellText
    )
}
