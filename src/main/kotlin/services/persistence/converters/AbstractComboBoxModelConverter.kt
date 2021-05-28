package services.persistence.converters

import com.intellij.util.xmlb.Converter
import javax.swing.ComboBoxModel

abstract class AbstractComboBoxModelConverter<T> : Converter<ComboBoxModel<T>>() {
    override fun toString(value: ComboBoxModel<T>): String? {
        return value.selectedItem.toString()
    }
}
