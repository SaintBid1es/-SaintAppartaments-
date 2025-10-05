package org.example.project.presentation.listitem

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import org.example.project.data.model.Listing
import org.example.project.data.provider.FavoriteProvider
import org.example.project.viewmodel.FavoriteViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ListingListItem(listing: Listing,navigator: Navigator?,
                     viewModel: FavoriteViewModel,changeFavorite:(Listing)->Unit) {
    val viewModel = remember { FavoriteViewModel() }
    val isFavorite by remember(listing.id) {
        derivedStateOf { viewModel.isFavorite(listing) }
    }
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .width(180.dp)
            .height(250.dp)
            .clickable(onClick = {
                //TODO  СДЕЛАТЬ НАВИГАЦИЮ НА КАРТОЧКУ с данными navigator.push()
            }),
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)

    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {

            Image(
                painter = painterResource(
                    resource = listing.imageId,
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(10.dp)))


            Spacer(modifier = Modifier.height(8.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = listing.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    modifier = Modifier.weight(1f).padding(end = 4.dp))
                }

            }

            Spacer(modifier = Modifier.height(4.dp))

            // Price
            Text(
                text = "${listing.price} $",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary)

            Spacer(modifier = Modifier.height(4.dp))

            // Location row
            if (listing.address != null) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        Icons.Filled.LocationOn,
                        contentDescription = "Location",
                        modifier = Modifier.size(14.dp))

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = listing.address,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        modifier = Modifier.weight(1f))
                }
            }
        }
    }

@Composable
@Preview
fun ListingPreview(){
    val listing = FavoriteProvider.listing
    val navigator = LocalNavigator.current
    val viewModel = remember { FavoriteViewModel() }
    ListingListItem(listing,navigator,viewModel,{})
}