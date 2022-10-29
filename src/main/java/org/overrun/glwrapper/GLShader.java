/*
 * MIT License
 *
 * Copyright (c) 2022 Overrun Organization
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.overrun.glwrapper;

import org.jetbrains.annotations.Nullable;
import org.overrun.glib.gl.GL;

import java.lang.foreign.Addressable;

/**
 * The OpenGL shader object.
 *
 * @author squid233
 * @since 0.1.0
 */
public class GLShader {
    private final int id;
    private final int type;

    /**
     * Creates the shader.
     *
     * @param type the shader type
     */
    public GLShader(int type) {
        this.type = type;
        id = GL.createShader(type);
    }

    public void compile() {
        GL.compileShader(id());
    }

    public void delete() {
        GL.deleteShader(id());
    }

    public void getInfoLog(int bufSize, Addressable length, Addressable infoLog) {
        GL.getShaderInfoLog(id(), bufSize, length, infoLog);
    }

    public void getInfoLog(int bufSize, int @Nullable [] length, String[] infoLog) {
        GL.getShaderInfoLog(id(), bufSize, length, infoLog);
    }

    public String getInfoLog(int bufSize, int @Nullable [] length) {
        return GL.getShaderInfoLog(id(), bufSize, length);
    }

    public String getInfoLog() {
        return GL.getShaderInfoLog(id());
    }

    public void getSource(int bufSize, Addressable length, Addressable source) {
        GL.getShaderSource(id(), bufSize, length, source);
    }

    public void getSource(int bufSize, int @Nullable [] length, String[] source) {
        GL.getShaderSource(id(), bufSize, length, source);
    }

    public String getSource(int bufSize, int @Nullable [] length) {
        return GL.getShaderSource(id(), bufSize, length);
    }

    public String getSource() {
        return GL.getShaderSource(id());
    }

    public void getiv(int pname, Addressable params) {
        GL.getShaderiv(id(), pname, params);
    }

    public void getiv(int pname, int[] params) {
        GL.getShaderiv(id(), pname, params);
    }

    public int geti(int pname) {
        return GL.getShaderi(id(), pname);
    }

    public boolean isShader() {
        return GL.isShader(id());
    }

    public void source(int count, Addressable string, Addressable length) {
        GL.shaderSource(id(), count, string, length);
    }

    public void source(String[] string) {
        GL.shaderSource(id(), string);
    }

    public void source(String string) {
        GL.shaderSource(id(), string);
    }

    public void specialize(Addressable pEntryPoint, int numSpecializationConstants, Addressable pConstantIndex, Addressable pConstantValue) {
        GL.specializeShader(id(), pEntryPoint, numSpecializationConstants, pConstantIndex, pConstantValue);
    }

    public void specialize(@Nullable String pEntryPoint, int @Nullable [] pConstantIndex, int @Nullable [] pConstantValue) {
        GL.specializeShader(id(), pEntryPoint, pConstantIndex, pConstantValue);
    }

    public void specialize(@Nullable String pEntryPoint) {
        GL.specializeShader(id(), pEntryPoint);
    }

    /**
     * Gets the id of this shader.
     *
     * @return the id of this shader
     */
    public int id() {
        return id;
    }

    /**
     * Gets the type of this shader.
     *
     * @return the type of this shader
     */
    public int type() {
        return type;
    }
}
