package com.example.fridgetracker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.asLiveData
import java.time.format.DateTimeFormatter

@Composable
fun FridgeContentsScreen(
    repository: ContentItemRepository,
    activity: FragmentActivity
) {
    val fridgeContentList by repository.allContentItems.asLiveData().observeAsState(initial = emptyList())
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(items = fridgeContentList) { currentItem ->
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(vertical = 3.dp, horizontal = 6.dp)
                    .clickable(
                        onClick = {
                            NewItemSheet(currentItem)
                                .show(activity.supportFragmentManager, "newContentTag")
                        }
                    )
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f).padding(12.dp),
                        text = currentItem.name,
                        fontSize = 24.sp
                    )
                    Text(
                        modifier = Modifier.padding(12.dp),
                        text = currentItem.expiryDate()!!
                            .format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                        fontSize = 24.sp,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}