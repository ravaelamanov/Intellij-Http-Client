package services.persistence.converters

import com.intellij.ui.CollectionComboBoxModel
import services.persistence.methodsList
import javax.swing.ComboBoxModel

class MethodsComboBoxModelConverter : AbstractComboBoxModelConverter<String>() {
    override fun fromString(value: String): ComboBoxModel<String> {
        return CollectionComboBoxModel(methodsList, value)
    }
}
