package services.persistence.converters

import com.intellij.ui.EnumComboBoxModel
import services.auth.AuthenticationProvider
import javax.swing.ComboBoxModel

class StrategiesComboBoxModelConverter : AbstractComboBoxModelConverter<AuthenticationProvider.Strategy>() {
    override fun fromString(value: String): ComboBoxModel<AuthenticationProvider.Strategy> {
        val enumComboBoxModel = EnumComboBoxModel(AuthenticationProvider.Strategy::class.java)
        enumComboBoxModel.setSelectedItem(AuthenticationProvider.Strategy.fromString(value))
        return enumComboBoxModel
    }
}
