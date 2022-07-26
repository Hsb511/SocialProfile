package com.team23.user.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team23.user.domain.models.ContactData

/**
 * The View that displays the contact information:
 * - a category such as email, phone or address
 * - the value of the data
 */
@Composable
fun ContactDataView(contactData: ContactData) {
    Column(modifier = Modifier.padding(12.dp, 8.dp)) {
        Text(
            text = stringResource(id = contactData.contactDataCategory.resId),
            color = MaterialTheme.colors.onSurface,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(0.dp, 4.dp)
        )
        Text(
            text = contactData.contactDataValue,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body2,
            lineHeight = 20.sp
        )
    }
}