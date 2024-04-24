package com.example.milchman

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * The horizontally scrollable table with header and content.
 * @param columnCount the count of columns in the table
 * @param cellWidth the width of column, can be configured based on index of the column.
 * @param data the data to populate table.
 * @param modifier the modifier to apply to this layout node.
 * @param headerCellContent a block which describes the header cell content.
 * @param cellContent a block which describes the cell content.
 */
@Composable
fun <T> Table(
    modifier: Modifier = Modifier,
    columnCount: Int,
    cellWidth: (index: Int) -> Dp,
    headers: List<String> = emptyList(),
    data: List<T>,
    headerCellContent: @Composable (index: Int) -> Unit,
    cellContent: @Composable (index: Int, item: T) -> Unit,
) {
    Surface(
        modifier = modifier
    ) {
        LazyRow(
            modifier = Modifier.padding(8.dp)
        ) {
            items(columnCount) { columnIndex ->
                Column {
                    // Header
                    Surface(
                        border = BorderStroke(1.dp, Color.LightGray),
                        contentColor = Color.Transparent,
                        modifier = Modifier.width(cellWidth(columnIndex))
                    ) {
                        headerCellContent(columnIndex)
                    }
                    // Content
                    data.indices.forEach { index ->
                        Surface(
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
                            contentColor = Color.Transparent,
                            modifier = Modifier.width(cellWidth(columnIndex))
                        ) {
                            cellContent(columnIndex, data[index])
                        }
                    }
                }
            }
        }
    }
}

