package me.partlysanestudios.partlysaneskies.render

import me.partlysanestudios.partlysaneskies.render.points.Point2d
import me.partlysanestudios.partlysaneskies.render.points.Point3d
import me.partlysanestudios.partlysaneskies.utils.ChatUtils
import net.minecraft.client.renderer.WorldRenderer

object RenderPrimitives {
    fun WorldRenderer.drawPerpendicularFaceFill(p1: Point2d, p2: Point2d, axis: Axis, constantDimension: Double) {
//        If the plane is on the x plane, then the z stays constant
        when (axis) {
            Axis.X_AXIS -> {
                val (x1, x2) = listOf(p1.x, p2.x).sorted()
                val (y1, y2) = listOf(p1.y, p2.y).sorted()

                this.pos(x1, y1, constantDimension).endVertex()
                this.pos(x2, y1, constantDimension).endVertex()
                this.pos(x2, y2, constantDimension).endVertex()
                this.pos(x1, y2, constantDimension).endVertex()
            }

            //        If the plane is on the y plane, then the y stays constant
            Axis.Y_AXIS -> {
                val (x1, x2) = listOf(p1.x, p2.x).sorted()
                val (y1, y2) = listOf(p1.y, p2.y).sorted()

                this.pos(x1, constantDimension, y1).endVertex()
                this.pos(x2, constantDimension, y1).endVertex()
                this.pos(x2, constantDimension, y2).endVertex()
                this.pos(x1, constantDimension, y2).endVertex()
            }

            //        If the plane is on the z plane, then the x stays constant
            Axis.Z_AXIS -> {
                val (x1, x2) = listOf(p1.x, p2.x).sorted()
                val (y1, y2) = listOf(p1.y, p2.y).sorted()

                this.pos(constantDimension, x1, y1).endVertex()
                this.pos(constantDimension, x2, y1).endVertex()
                this.pos(constantDimension, x2, y2).endVertex()
                this.pos(constantDimension, x1, y2).endVertex()
            }
        }
    }


    fun WorldRenderer.drawPerpendicularFaceOutline(p1: Point2d, p2: Point2d, axis: Axis, constantDimension: Double) {
        //        If the plane is on the x plane, then the z stays constant
        when (axis) {
            Axis.X_AXIS -> {
                val (x1, x2) = listOf(p1.x, p2.x).sorted()
                val (y1, y2) = listOf(p1.y, p2.y).sorted()

                this.pos(x1, y1, constantDimension).endVertex()
                this.pos(x2, y1, constantDimension).endVertex()

                this.pos(x1, y1, constantDimension).endVertex()
                this.pos(x1, y2, constantDimension).endVertex()

                this.pos(x2, y1, constantDimension).endVertex()
                this.pos(x2, y2, constantDimension).endVertex()

                this.pos(x1, y2, constantDimension).endVertex()
                this.pos(x2, y2, constantDimension).endVertex()
            }

            //        If the plane is on the y plane, then the y stays constant
            Axis.Y_AXIS -> {
                val (x1, x2) = listOf(p1.x, p2.x).sorted()
                val (y1, y2) = listOf(p1.y, p2.y).sorted()

                this.pos(x1, constantDimension, y1).endVertex()
                this.pos(x2, constantDimension, y1).endVertex()

                this.pos(x1, constantDimension, y1).endVertex()
                this.pos(x1, constantDimension, y2).endVertex()

                this.pos(x2, constantDimension, y1).endVertex()
                this.pos(x2, constantDimension, y2).endVertex()

                this.pos(x1, constantDimension, y2).endVertex()
                this.pos(x2, constantDimension, y2).endVertex()
            }

            //        If the plane is on the z plane, then the x stays constant
            Axis.Z_AXIS -> {
                val (x1, x2) = listOf(p1.x, p2.x).sorted()
                val (y1, y2) = listOf(p1.y, p2.y).sorted()
                ChatUtils.sendClientMessage("y" + x1)


                this.pos(constantDimension, x1, y1).endVertex()
                this.pos(constantDimension, x2, y1).endVertex()

                this.pos(constantDimension, x1, y1).endVertex()
                this.pos(constantDimension, x1, y2).endVertex()

                this.pos(constantDimension, x2, y1).endVertex()
                this.pos(constantDimension, x2, y2).endVertex()

                this.pos(constantDimension, x1, y2).endVertex()
                this.pos(constantDimension, x2, y2).endVertex()
            }
        }
    }

    fun WorldRenderer.drawBoxOutline(p1: Point3d, p2: Point3d) {
        val (x1, x2) = listOf(p1.x, p2.x).sorted()
        val (y1, y2) = listOf(p1.y, p2.y).sorted()
        val (z1, z2) = listOf(p1.z, p2.z).sorted()

//        x face front (z is constant)
        this.drawPerpendicularFaceOutline(Point2d(x1, y1), Point2d(x2, y2), Axis.X_AXIS, z1)
//        x face back (z is constant)
        this.drawPerpendicularFaceOutline(Point2d(x1, y1), Point2d(x2, y2), Axis.X_AXIS, z2)
//        y face front (y is constant)
        this.drawPerpendicularFaceOutline(Point2d(x1, z1), Point2d(x2, z2), Axis.Y_AXIS, y1)
//        y face back (y is constant)
        this.drawPerpendicularFaceOutline(Point2d(x1, z1), Point2d(x2, z2), Axis.Y_AXIS, y2)
//        z face front (x is constant)
        this.drawPerpendicularFaceOutline(Point2d(y1, z1), Point2d(y2, z2), Axis.Z_AXIS, x1)
//        z face back (x is constant)
        this.drawPerpendicularFaceOutline(Point2d(y1, z1), Point2d(y2, z2), Axis.Z_AXIS, x2)
    }

    fun WorldRenderer.drawBoxFill(p1: Point3d, p2: Point3d) {
        val (x1, x2) = listOf(p1.x, p2.x).sorted()
        val (y1, y2) = listOf(p1.y, p2.y).sorted()
        val (z1, z2) = listOf(p1.z, p2.z).sorted()

//        x face front (z is constant)
        this.drawPerpendicularFaceFill(Point2d(x1, y1), Point2d(x2, y2), Axis.X_AXIS, z1)
//        x face back (z is constant)
        this.drawPerpendicularFaceFill(Point2d(x1, y1), Point2d(x2, y2), Axis.X_AXIS, z2)
//        y face front (y is constant)
        this.drawPerpendicularFaceFill(Point2d(x1, z1), Point2d(x2, z2), Axis.Y_AXIS, y1)
//        y face back (y is constant)
        this.drawPerpendicularFaceFill(Point2d(x1, z1), Point2d(x2, z2), Axis.Y_AXIS, y2)
//        x face front (x is constant)
        this.drawPerpendicularFaceFill(Point2d(y1, z1), Point2d(y2, z2), Axis.Z_AXIS, x1)
//        x face back (x is constant)
        this.drawPerpendicularFaceFill(Point2d(y1, z1), Point2d(y2, z2), Axis.Z_AXIS, x2)
    }


    enum class Axis {
        X_AXIS,
        Y_AXIS,
        Z_AXIS
    }

}