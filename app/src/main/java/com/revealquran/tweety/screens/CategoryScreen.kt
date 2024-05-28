package com.revealquran.tweety.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.revealquran.tweety.R
import com.revealquran.tweety.viewmodel.CategoryViewModel

@Composable
fun CategoryScreen(onClick: (category: String) -> Unit) {
    // viewModel() method by default CategoryViewModel class ka object banay ga or is variable ko assign kr dy ga
    // But here we use Naviagtion with hilt so we have to use hiltViewModel()
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    // jesy jesy api ky through is ma data ata rahy ga to hamari state update hoti rahy gi or jesy jesy state update hogi wesy wesy ui update hoti rahy gi
    val categories: State<List<String>> = categoryViewModel.categories.collectAsState()

    if (categories.value.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center) {
            Text(text = "Loading...", style = MaterialTheme.typography.bodySmall)
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            items(categories.value.distinct()) {
                CategoryItem(category = it, onClick)
            }
        }
    }
}

@Composable
fun CategoryItem(category: String, onClick: (category: String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(160.dp)
            .clip(RoundedCornerShape(8.dp))
            .paint(
                painter = painterResource(id = R.drawable.wave_img),
                contentScale = ContentScale.Crop
            )
            .border(1.dp, Color(0xFFEEEEEE))
            .clickable { onClick(category) },
        contentAlignment = Alignment.BottomCenter
    )
    {
        Text(
            text = category,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier.padding(0.dp, 20.dp),
            style = MaterialTheme.typography.bodySmall
        )
    }
}