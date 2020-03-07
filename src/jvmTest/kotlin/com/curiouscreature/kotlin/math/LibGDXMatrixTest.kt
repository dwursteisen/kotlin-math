package com.curiouscreature.kotlin.math

import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.GdxNativesLoader
import kotlin.test.Test

/**
 * Test the Mat4 implementation against the libGDX Matrix4 implementation.
 *
 * Results should be equals.
 */
class LibGDXMatrixTest {

    init {
        GdxNativesLoader.load()
    }

    // https://www.andre-gaschler.com/rotationconverter/
    @Test
    fun identityMatrix() {
        assertEquals(Matrix4().idt(), Mat4.identity())
    }

    @Test
    fun translation() {
        assertEquals(Matrix4().translate(1f, 2f, 3f), translation(Float3(1f, 2f, 3f)))
    }

    @Test
    fun transpose() {
        assertEquals(Matrix4(
            floatArrayOf(
                1f, 2f, 3f, 4f,
                5f, 6f, 7f, 8f,
                9f, 10f, 11f, 12f,
                13f, 14f, 15f, 16f
            )
        ), transpose(Mat4.of(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f, 12f, 13f, 14f, 15f, 16f)))
    }

    @Test
    fun rotationX() {
        assertEquals(
            Matrix4().rotate(Vector3(1f, 0f, 0f), 25f),
            Mat4.identity() * rotation(Float3(1f, 0f, 0f), 25f)
        )
    }

    @Test
    fun rotationY() {
        assertEquals(
            Matrix4().rotate(Vector3(0f, 1f, 0f), 25f),
            Mat4.identity() * rotation(Float3(0f, 1f, 0f), 25f)
        )
    }

    @Test
    fun rotationZ() {
        assertEquals(
            Matrix4().rotate(Vector3(0f, 0f, 1f), 25f),
            Mat4.identity() * rotation(Float3(0f, 0f, 1f), 25f)
        )
    }

    @Test
    fun rotationXY() {
        assertEquals(
            Matrix4().rotate(Vector3(1f, 1f, 0f), 25f),
            Mat4.identity() * rotation(normalize(Float3(1f, 1f, 0f)), 25f)
        )
    }

    @Test
    fun rotationXYZ() {
        assertEquals(
            Matrix4().rotate(Vector3(1f, 1f, 1f), 25f),
            Mat4.identity() * rotation(normalize(Float3(1f, 1f, 1f)), 25f)
        )
    }

    fun assertEquals(expected: Matrix4, actual: Mat4) {
        val kotlinMath = actual.toFloatArray()
        val libgdx = expected.`val`

        assertArrayEquals(kotlinMath, libgdx)
    }
}
