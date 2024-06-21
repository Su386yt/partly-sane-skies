//
// Written by Su386.
// See LICENSE for copyright and license notices.
//
package me.partlysanestudios.partlysaneskies.render.gui.components

import cc.polyfrost.oneconfig.config.core.OneColor
import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.components.UIImage
import gg.essential.elementa.components.UIWrappedText
import gg.essential.elementa.constraints.*
import gg.essential.elementa.dsl.childOf
import gg.essential.elementa.dsl.constrain
import gg.essential.elementa.dsl.constraint
import gg.essential.elementa.dsl.percent
import gg.essential.elementa.events.UIClickEvent
import me.partlysanestudios.partlysaneskies.features.themes.ThemeManager.currentButtonUIImage
import me.partlysanestudios.partlysaneskies.features.themes.ThemeManager.getCurrentButtonUIImage
import java.awt.Color
import java.util.function.Consumer

class PSSButton {

    private var backgroundBlock: UIBlock
    private var buttonTexture: UIImage
    private var textComponent: UIWrappedText
    private var text: String

    constructor() {
        text = ""
        backgroundBlock = UIBlock().constrain {
            color = Color(0, 0, 0, 0).constraint
        }

        buttonTexture = currentButtonUIImage.constrain {
            width = 100.percent
            height = 100.percent
            x = CenterConstraint()
            y = CenterConstraint()
        } childOf backgroundBlock

        textComponent = UIWrappedText(text, false, Color(0, 0, 0, 0), true).constrain {
            width = 100.percent
            height = 100.percent
            x = CenterConstraint()
            y = CenterConstraint()
            color = Color.white.constraint
        } childOf buttonTexture
    }

    constructor(color: Color) : this(OneColor(color))

    constructor(color: OneColor) {
        text = ""

        backgroundBlock = UIBlock().constrain {
            this.color = color.toJavaColor().constraint
        }

        buttonTexture = getCurrentButtonUIImage(color).constrain {
            width = 100.percent
            height = 100.percent
            x = CenterConstraint()
            y = CenterConstraint()
        } childOf backgroundBlock

        textComponent = UIWrappedText(text, false, Color(0, 0, 0, 0), true).constrain {
            width = 100.percent
            height = 100.percent
            x = CenterConstraint()
            y = CenterConstraint()
            this.color = Color.white.constraint
        } childOf buttonTexture

    }

    fun setHeight(height: HeightConstraint): PSSButton {
        backgroundBlock.setHeight(height)
        return this
    }

    fun setWidth(width: WidthConstraint): PSSButton {
        backgroundBlock.setWidth(width)
        return this
    }

    fun setX(xPos: XConstraint): PSSButton {
        backgroundBlock.setX(xPos)
        return this
    }

    fun setY(yPos: YConstraint): PSSButton {
        backgroundBlock.setY(yPos)
        return this
    }

    fun setChildOf(parent: UIComponent): PSSButton {
        backgroundBlock.setChildOf(parent)
        return this
    }

    fun setColor(color: Color): PSSButton {
        return setColor(OneColor(color))
    }

    fun setColor(color: OneColor): PSSButton {
        backgroundBlock.removeChild(buttonTexture)
        buttonTexture = getCurrentButtonUIImage(color).constrain {
            width = 100.percent
            height = 100.percent
            x = CenterConstraint()
            y = CenterConstraint()
        } childOf backgroundBlock
        backgroundBlock.setColor(color.toJavaColor())
        return this
    }

    fun setDefaultColor(): PSSButton {
        backgroundBlock.removeChild(buttonTexture)
        buttonTexture = currentButtonUIImage.constrain {
            width = 100.percent
            height = 100.percent
            x = CenterConstraint()
            y = CenterConstraint()
        } childOf backgroundBlock
        backgroundBlock.setColor(Color(0, 0, 0, 0))
        return this
    }

    fun setText(text: String): PSSButton {
        textComponent.setText(text)
        this.text = text
        return this
    }

    fun setTextScale(scale: HeightConstraint): PSSButton {
        textComponent.setTextScale(scale)
        return this
    }

    fun onMouseClickConsumer(method: Consumer<UIClickEvent>): PSSButton {
        backgroundBlock.onMouseClickConsumer(method)
        return this
    }

    val component: UIComponent
        get() = buttonTexture

    fun setBackgroundVisibility(value: Boolean): PSSButton {
        if (value) {
            buttonTexture.unhide(true)
            for (child in buttonTexture.children) {
                child.unhide(true)
            }
        } else {
            buttonTexture.hide()
            for (child in buttonTexture.children) {
                child.unhide(true)
            }
        }
        return this
    }

    fun insertComponentBeforeBackground(component: UIComponent): PSSButton {
        backgroundBlock.insertChildBefore(component, buttonTexture)
        return this
    }
}
