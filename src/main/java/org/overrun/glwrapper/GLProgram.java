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
import java.lang.foreign.MemorySegment;

/**
 * The OpenGL program object.
 *
 * @author squid233
 * @since 0.1.0
 */
public class GLProgram {
    /**
     * The zero program object. Only for {@code glUseProgram(0)}
     */
    public static final GLProgram ZERO = new GLProgram(0);
    private final int id;

    public GLProgram() {
        id = GL.createProgram();
    }

    private GLProgram(int id) {
        this.id = id;
    }

    public void attachShader(int shader) {
        GL.attachShader(id(), shader);
    }

    public void attachShader(GLShader shader) {
        attachShader(shader.id());
    }

    public void bindAttribLocation(int index, Addressable name) {
        GL.bindAttribLocation(id(), index, name);
    }

    public void bindAttribLocation(int index, String name) {
        GL.bindAttribLocation(id(), index, name);
    }

    public void delete() {
        GL.deleteProgram(id());
    }

    public void detachShader(int shader) {
        GL.detachShader(id(), shader);
    }

    public void detachShader(GLShader shader) {
        detachShader(shader.id());
    }

    public void getActiveAttrib(int index, int bufSize, Addressable length, Addressable size, Addressable type, Addressable name) {
        GL.getActiveAttrib(id(), index, bufSize, length, size, type, name);
    }

    public void getActiveAttrib(int index, int bufSize, int @Nullable [] length, int[] size, int[] type, String[] name) {
        GL.getActiveAttrib(id(), index, bufSize, length, size, type, name);
    }

    public void getActiveUniform(int index, int bufSize, Addressable length, Addressable size, Addressable type, Addressable name) {
        GL.getActiveUniform(id(), index, bufSize, length, size, type, name);
    }

    public void getActiveUniform(int index, int bufSize, int @Nullable [] length, int[] size, int[] type, String[] name) {
        GL.getActiveUniform(id(), index, bufSize, length, size, type, name);
    }

    public void getAttachedShaders(int maxCount, Addressable count, Addressable shaders) {
        GL.getAttachedShaders(id(), maxCount, count, shaders);
    }

    public void getAttachedShaders(int @Nullable [] count, int[] shaders) {
        GL.getAttachedShaders(id(), count, shaders);
    }

    public int getAttribLocation(Addressable name) {
        return GL.getAttribLocation(id(), name);
    }

    public int getAttribLocation(String name) {
        return GL.getAttribLocation(id(), name);
    }

    public void getInfoLog(int bufSize, Addressable length, Addressable infoLog) {
        GL.getProgramInfoLog(id(), bufSize, length, infoLog);
    }

    public void getInfoLog(int bufSize, int @Nullable [] length, String[] infoLog) {
        GL.getProgramInfoLog(id(), bufSize, length, infoLog);
    }

    public String getInfoLog(int bufSize, int @Nullable [] length) {
        return GL.getProgramInfoLog(id(), bufSize, length);
    }

    public String getInfoLog() {
        return GL.getProgramInfoLog(id());
    }

    public void getiv(int pname, Addressable params) {
        GL.getProgramiv(id(), pname, params);
    }

    public void getiv(int pname, int[] params) {
        GL.getProgramiv(id(), pname, params);
    }

    public int geti(int pname) {
        return GL.getProgrami(id(), pname);
    }

    public int getUniformLocation(Addressable name) {
        return GL.getUniformLocation(id(), name);
    }

    public int getUniformLocation(String name) {
        return GL.getUniformLocation(id(), name);
    }

    public void getUniformfv(int location, Addressable params) {
        GL.getUniformfv(id(), location, params);
    }

    public void getUniformfv(int location, float[] params) {
        GL.getUniformfv(id(), location, params);
    }

    public float getUniformf(int location) {
        return GL.getUniformf(id(), location);
    }

    public void getUniformiv(int location, Addressable params) {
        GL.getUniformiv(id(), location, params);
    }

    public void getUniformiv(int location, int[] params) {
        GL.getUniformiv(id(), location, params);
    }

    public int getUniformi(int location) {
        return GL.getUniformi(id(), location);
    }

    public boolean isProgram() {
        return GL.isProgram(id());
    }

    public void link() {
        GL.linkProgram(id());
    }

    public void use() {
        GL.useProgram(id());
    }

    public void validate() {
        GL.validateProgram(id());
    }

    public void bindFragDataLocation(int color, Addressable name) {
        GL.bindFragDataLocation(id(), color, name);
    }

    public void bindFragDataLocation(int color, String name) {
        GL.bindFragDataLocation(id(), color, name);
    }

    public int getFragDataLocation(Addressable name) {
        return GL.getFragDataLocation(id(), name);
    }

    public int getFragDataLocation(String name) {
        return GL.getFragDataLocation(id(), name);
    }

    public void getTransformFeedbackVarying(int index, int bufSize, Addressable length, Addressable size, Addressable type, Addressable name) {
        GL.getTransformFeedbackVarying(id(), index, bufSize, length, size, type, name);
    }

    public void getTransformFeedbackVarying(int index, int bufSize, int @Nullable [] length, int[] size, int[] type, String[] name) {
        GL.getTransformFeedbackVarying(id(), index, bufSize, length, size, type, name);
    }

    public void getUniformuiv(int location, Addressable params) {
        GL.getUniformuiv(id(), location, params);
    }

    public void getUniformuiv(int location, int[] params) {
        GL.getUniformuiv(id(), location, params);
    }

    public int getUniformui(int location) {
        return GL.getUniformui(id(), location);
    }

    public void transformFeedbackVaryings(int count, Addressable varyings, int bufferMode) {
        GL.transformFeedbackVaryings(id(), count, varyings, bufferMode);
    }

    public void transformFeedbackVaryings(String[] varyings, int bufferMode) {
        GL.transformFeedbackVaryings(id(), varyings, bufferMode);
    }

    public void getActiveUniformBlockName(int uniformBlockIndex, int bufSize, Addressable length, Addressable uniformBlockName) {
        GL.getActiveUniformBlockName(id(), uniformBlockIndex, bufSize, length, uniformBlockName);
    }

    public void getActiveUniformBlockName(int uniformBlockIndex, int bufSize, int @Nullable [] length, String[] uniformBlockName) {
        GL.getActiveUniformBlockName(id(), uniformBlockIndex, bufSize, length, uniformBlockName);
    }

    public String getActiveUniformBlockName(int uniformBlockIndex, int bufSize) {
        return GL.getActiveUniformBlockName(id(), uniformBlockIndex, bufSize);
    }

    public void getActiveUniformBlockiv(int uniformBlockIndex, int pname, Addressable params) {
        GL.getActiveUniformBlockiv(id(), uniformBlockIndex, pname, params);
    }

    public void getActiveUniformBlockiv(int uniformBlockIndex, int pname, int[] params) {
        GL.getActiveUniformBlockiv(id(), uniformBlockIndex, pname, params);
    }

    public void getActiveUniformName(int uniformIndex, int bufSize, Addressable length, Addressable uniformName) {
        GL.getActiveUniformName(id(), uniformIndex, bufSize, length, uniformName);
    }

    public void getActiveUniformName(int uniformIndex, int bufSize, int @Nullable [] length, String[] uniformName) {
        GL.getActiveUniformName(id(), uniformIndex, bufSize, length, uniformName);
    }

    public String getActiveUniformName(int uniformIndex, int bufSize) {
        return GL.getActiveUniformName(id(), uniformIndex, bufSize);
    }

    public void getActiveUniformsiv(int uniformCount, Addressable uniformIndices, int pname, Addressable params) {
        GL.getActiveUniformsiv(id(), uniformCount, uniformIndices, pname, params);
    }

    public void getActiveUniformsiv(int[] uniformIndices, int pname, int[] params) {
        GL.getActiveUniformsiv(id(), uniformIndices, pname, params);
    }

    public int getActiveUniformi(int uniformIndex, int pname) {
        return GL.getActiveUniformi(id(), uniformIndex, pname);
    }

    public int getUniformBlockIndex(Addressable uniformBlockName) {
        return GL.getUniformBlockIndex(id(), uniformBlockName);
    }

    public int getUniformBlockIndex(String uniformBlockName) {
        return GL.getUniformBlockIndex(id(), uniformBlockName);
    }

    public void getUniformIndices(int uniformCount, Addressable uniformNames, Addressable uniformIndices) {
        GL.getUniformIndices(id(), uniformCount, uniformNames, uniformIndices);
    }

    public void getUniformIndices(String[] uniformNames, int[] uniformIndices) {
        GL.getUniformIndices(id(), uniformNames, uniformIndices);
    }

    public int getUniformIndex(String uniformName) {
        return GL.getUniformIndex(id(), uniformName);
    }

    public void uniformBlockBinding(int uniformBlockIndex, int uniformBlockBinding) {
        GL.uniformBlockBinding(id(), uniformBlockIndex, uniformBlockBinding);
    }

    public void bindFragDataLocationIndexed(int colorNumber, int index, Addressable name) {
        GL.bindFragDataLocationIndexed(id(), colorNumber, index, name);
    }

    public void bindFragDataLocationIndexed(int colorNumber, int index, String name) {
        GL.bindFragDataLocationIndexed(id(), colorNumber, index, name);
    }

    public int getFragDataIndex(Addressable name) {
        return GL.getFragDataIndex(id(), name);
    }

    public int getFragDataIndex(String name) {
        return GL.getFragDataIndex(id(), name);
    }

    public void getActiveSubroutineName(int shaderType, int index, int bufSize, Addressable length, Addressable name) {
        GL.getActiveSubroutineName(id(), shaderType, index, bufSize, length, name);
    }

    public String getActiveSubroutineName(int shaderType, int index, int bufSize) {
        return GL.getActiveSubroutineName(id(), shaderType, index, bufSize);
    }

    public void getActiveSubroutineUniformName(int shaderType, int index, int bufSize, Addressable length, Addressable name) {
        GL.getActiveSubroutineUniformName(id(), shaderType, index, bufSize, length, name);
    }

    public String getActiveSubroutineUniformName(int shaderType, int index, int bufSize) {
        return GL.getActiveSubroutineUniformName(id(), shaderType, index, bufSize);
    }

    public void getActiveSubroutineUniformiv(int shaderType, int index, int pname, Addressable values) {
        GL.getActiveSubroutineUniformiv(id(), shaderType, index, pname, values);
    }

    public void getActiveSubroutineUniformiv(int shaderType, int index, int pname, int[] values) {
        GL.getActiveSubroutineUniformiv(id(), shaderType, index, pname, values);
    }

    public int getActiveSubroutineUniformi(int shaderType, int index, int pname) {
        return GL.getActiveSubroutineUniformi(id(), shaderType, index, pname);
    }

    public void getProgramStageiv(int shaderType, int pname, Addressable values) {
        GL.getProgramStageiv(id(), shaderType, pname, values);
    }

    public int getProgramStagei(int shaderType, int pname) {
        return GL.getProgramStagei(id(), shaderType, pname);
    }

    public int getSubroutineIndex(int shaderType, Addressable name) {
        return GL.getSubroutineIndex(id(), shaderType, name);
    }

    public int getSubroutineIndex(int shaderType, String name) {
        return GL.getSubroutineIndex(id(), shaderType, name);
    }

    public int getSubroutineUniformLocation(int shaderType, Addressable name) {
        return GL.getSubroutineUniformLocation(id(), shaderType, name);
    }

    public int getSubroutineUniformLocation(int shaderType, String name) {
        return GL.getSubroutineUniformLocation(id(), shaderType, name);
    }

    public void getUniformdv(int location, Addressable params) {
        GL.getUniformdv(id(), location, params);
    }

    public void getUniformdv(int location, double[] params) {
        GL.getUniformdv(id(), location, params);
    }

    public double getUniformd(int location) {
        return GL.getUniformd(id(), location);
    }

    public void getBinary(int bufSize, Addressable length, Addressable binaryFormat, Addressable binary) {
        GL.getProgramBinary(id(), bufSize, length, binaryFormat, binary);
    }

    public void getBinary(int bufSize, int @Nullable [] length, int[] binaryFormat, Addressable binary) {
        GL.getProgramBinary(id(), bufSize, length, binaryFormat, binary);
    }

    public void getBinary(int @Nullable [] length, int[] binaryFormat, MemorySegment binary) {
        GL.getProgramBinary(id(), length, binaryFormat, binary);
    }

    public void binary(int binaryFormat, Addressable binary, int length) {
        GL.programBinary(id(), binaryFormat, binary, length);
    }

    public void binary(int binaryFormat, MemorySegment binary) {
        GL.programBinary(id(), binaryFormat, binary);
    }

    public void parameteri(int pname, int value) {
        GL.programParameteri(id(), pname, value);
    }

    public void uniform1d(int location, double v0) {
        GL.programUniform1d(id(), location, v0);
    }

    public void uniform1dv(int location, int count, Addressable value) {
        GL.programUniform1dv(id(), location, count, value);
    }

    public void uniform1dv(int location, double[] value) {
        GL.programUniform1dv(id(), location, value);
    }

    public void uniform1f(int location, float v0) {
        GL.programUniform1f(id(), location, v0);
    }

    public void uniform1fv(int location, int count, Addressable value) {
        GL.programUniform1fv(id(), location, count, value);
    }

    public void uniform1fv(int location, float[] value) {
        GL.programUniform1fv(id(), location, value);
    }

    public void uniform1i(int location, int v0) {
        GL.programUniform1i(id(), location, v0);
    }

    public void uniform1iv(int location, int count, Addressable value) {
        GL.programUniform1iv(id(), location, count, value);
    }

    public void uniform1iv(int location, int[] value) {
        GL.programUniform1iv(id(), location, value);
    }

    public void uniform1ui(int location, int v0) {
        GL.programUniform1ui(id(), location, v0);
    }

    public void uniform1uiv(int location, int count, Addressable value) {
        GL.programUniform1uiv(id(), location, count, value);
    }

    public void uniform1uiv(int location, int[] value) {
        GL.programUniform1uiv(id(), location, value);
    }

    public void uniform2d(int location, double v0, double v1) {
        GL.programUniform2d(id(), location, v0, v1);
    }

    public void uniform2dv(int location, int count, Addressable value) {
        GL.programUniform2dv(id(), location, count, value);
    }

    public void uniform2dv(int location, double[] value) {
        GL.programUniform2dv(id(), location, value);
    }

    public void uniform2f(int location, float v0, float v1) {
        GL.programUniform2f(id(), location, v0, v1);
    }

    public void uniform2fv(int location, int count, Addressable value) {
        GL.programUniform2fv(id(), location, count, value);
    }

    public void uniform2fv(int location, float[] value) {
        GL.programUniform2fv(id(), location, value);
    }

    public void uniform2i(int location, int v0, int v1) {
        GL.programUniform2i(id(), location, v0, v1);
    }

    public void uniform2iv(int location, int count, Addressable value) {
        GL.programUniform2iv(id(), location, count, value);
    }

    public void uniform2iv(int location, int[] value) {
        GL.programUniform2iv(id(), location, value);
    }

    public void uniform2ui(int location, int v0, int v1) {
        GL.programUniform2ui(id(), location, v0, v1);
    }

    public void uniform2uiv(int location, int count, Addressable value) {
        GL.programUniform2uiv(id(), location, count, value);
    }

    public void uniform2uiv(int location, int[] value) {
        GL.programUniform2uiv(id(), location, value);
    }

    public void uniform3d(int location, double v0, double v1, double v2) {
        GL.programUniform3d(id(), location, v0, v1, v2);
    }

    public void uniform3dv(int location, int count, Addressable value) {
        GL.programUniform3dv(id(), location, count, value);
    }

    public void uniform3dv(int location, double[] value) {
        GL.programUniform3dv(id(), location, value);
    }

    public void uniform3f(int location, float v0, float v1, float v2) {
        GL.programUniform3f(id(), location, v0, v1, v2);
    }

    public void uniform3fv(int location, int count, Addressable value) {
        GL.programUniform3fv(id(), location, count, value);
    }

    public void uniform3fv(int location, float[] value) {
        GL.programUniform3fv(id(), location, value);
    }

    public void uniform3i(int location, int v0, int v1, int v2) {
        GL.programUniform3i(id(), location, v0, v1, v2);
    }

    public void uniform3iv(int location, int count, Addressable value) {
        GL.programUniform3iv(id(), location, count, value);
    }

    public void uniform3iv(int location, int[] value) {
        GL.programUniform3iv(id(), location, value);
    }

    public void uniform3ui(int location, int v0, int v1, int v2) {
        GL.programUniform3ui(id(), location, v0, v1, v2);
    }

    public void uniform3uiv(int location, int count, Addressable value) {
        GL.programUniform3uiv(id(), location, count, value);
    }

    public void uniform3uiv(int location, int[] value) {
        GL.programUniform3uiv(id(), location, value);
    }

    public void uniform4d(int location, double v0, double v1, double v2, double v3) {
        GL.programUniform4d(id(), location, v0, v1, v2, v3);
    }

    public void uniform4dv(int location, int count, Addressable value) {
        GL.programUniform4dv(id(), location, count, value);
    }

    public void uniform4dv(int location, double[] value) {
        GL.programUniform4dv(id(), location, value);
    }

    public void uniform4f(int location, float v0, float v1, float v2, float v3) {
        GL.programUniform4f(id(), location, v0, v1, v2, v3);
    }

    public void uniform4fv(int location, int count, Addressable value) {
        GL.programUniform4fv(id(), location, count, value);
    }

    public void uniform4fv(int location, float[] value) {
        GL.programUniform4fv(id(), location, value);
    }

    public void uniform4i(int location, int v0, int v1, int v2, int v3) {
        GL.programUniform4i(id(), location, v0, v1, v2, v3);
    }

    public void uniform4iv(int location, int count, Addressable value) {
        GL.programUniform4iv(id(), location, count, value);
    }

    public void uniform4iv(int location, int[] value) {
        GL.programUniform4iv(id(), location, value);
    }

    public void uniform4ui(int location, int v0, int v1, int v2, int v3) {
        GL.programUniform4ui(id(), location, v0, v1, v2, v3);
    }

    public void uniform4uiv(int location, int count, Addressable value) {
        GL.programUniform4uiv(id(), location, count, value);
    }

    public void uniform4uiv(int location, int[] value) {
        GL.programUniform4uiv(id(), location, value);
    }

    public void uniformMatrix2dv(int location, int count, boolean transpose, Addressable value) {
        GL.programUniformMatrix2dv(id(), location, count, transpose, value);
    }

    public void uniformMatrix2dv(int location, int count, boolean transpose, double[] value) {
        GL.programUniformMatrix2dv(id(), location, count, transpose, value);
    }

    public void uniformMatrix2dv(int location, boolean transpose, double[] value) {
        GL.programUniformMatrix2dv(id(), location, transpose, value);
    }

    public void uniformMatrix2fv(int location, int count, boolean transpose, Addressable value) {
        GL.programUniformMatrix2fv(id(), location, count, transpose, value);
    }

    public void uniformMatrix2fv(int location, int count, boolean transpose, float[] value) {
        GL.programUniformMatrix2fv(id(), location, count, transpose, value);
    }

    public void uniformMatrix2fv(int location, boolean transpose, float[] value) {
        GL.programUniformMatrix2fv(id(), location, transpose, value);
    }

    public void uniformMatrix2x3dv(int location, int count, boolean transpose, Addressable value) {
        GL.programUniformMatrix2x3dv(id(), location, count, transpose, value);
    }

    public void uniformMatrix2x3dv(int location, int count, boolean transpose, double[] value) {
        GL.programUniformMatrix2x3dv(id(), location, count, transpose, value);
    }

    public void uniformMatrix2x3dv(int location, boolean transpose, double[] value) {
        GL.programUniformMatrix2x3dv(id(), location, transpose, value);
    }

    public void uniformMatrix2x3fv(int location, int count, boolean transpose, Addressable value) {
        GL.programUniformMatrix2x3fv(id(), location, count, transpose, value);
    }

    public void uniformMatrix2x3fv(int location, int count, boolean transpose, float[] value) {
        GL.programUniformMatrix2x3fv(id(), location, count, transpose, value);
    }

    public void uniformMatrix2x3fv(int location, boolean transpose, float[] value) {
        GL.programUniformMatrix2x3fv(id(), location, transpose, value);
    }

    public void uniformMatrix2x4dv(int location, int count, boolean transpose, Addressable value) {
        GL.programUniformMatrix2x4dv(id(), location, count, transpose, value);
    }

    public void uniformMatrix2x4dv(int location, int count, boolean transpose, double[] value) {
        GL.programUniformMatrix2x4dv(id(), location, count, transpose, value);
    }

    public void uniformMatrix2x4dv(int location, boolean transpose, double[] value) {
        GL.programUniformMatrix2x4dv(id(), location, transpose, value);
    }

    public void uniformMatrix2x4fv(int location, int count, boolean transpose, Addressable value) {
        GL.programUniformMatrix2x4fv(id(), location, count, transpose, value);
    }

    public void uniformMatrix2x4fv(int location, int count, boolean transpose, float[] value) {
        GL.programUniformMatrix2x4fv(id(), location, count, transpose, value);
    }

    public void uniformMatrix2x4fv(int location, boolean transpose, float[] value) {
        GL.programUniformMatrix2x4fv(id(), location, transpose, value);
    }

    public void uniformMatrix3dv(int location, int count, boolean transpose, Addressable value) {
        GL.programUniformMatrix3dv(id(), location, count, transpose, value);
    }

    public void uniformMatrix3dv(int location, int count, boolean transpose, double[] value) {
        GL.programUniformMatrix3dv(id(), location, count, transpose, value);
    }

    public void uniformMatrix3dv(int location, boolean transpose, double[] value) {
        GL.programUniformMatrix3dv(id(), location, transpose, value);
    }

    public void uniformMatrix3fv(int location, int count, boolean transpose, Addressable value) {
        GL.programUniformMatrix3fv(id(), location, count, transpose, value);
    }

    public void uniformMatrix3fv(int location, int count, boolean transpose, float[] value) {
        GL.programUniformMatrix3fv(id(), location, count, transpose, value);
    }

    public void uniformMatrix3fv(int location, boolean transpose, float[] value) {
        GL.programUniformMatrix3fv(id(), location, transpose, value);
    }

    public void uniformMatrix3x2dv(int location, int count, boolean transpose, Addressable value) {
        GL.programUniformMatrix3x2dv(id(), location, count, transpose, value);
    }

    public void uniformMatrix3x2dv(int location, int count, boolean transpose, double[] value) {
        GL.programUniformMatrix3x2dv(id(), location, count, transpose, value);
    }

    public void uniformMatrix3x2dv(int location, boolean transpose, double[] value) {
        GL.programUniformMatrix3x2dv(id(), location, transpose, value);
    }

    public void uniformMatrix3x2fv(int location, int count, boolean transpose, Addressable value) {
        GL.programUniformMatrix3x2fv(id(), location, count, transpose, value);
    }

    public void uniformMatrix3x2fv(int location, int count, boolean transpose, float[] value) {
        GL.programUniformMatrix3x2fv(id(), location, count, transpose, value);
    }

    public void uniformMatrix3x2fv(int location, boolean transpose, float[] value) {
        GL.programUniformMatrix3x2fv(id(), location, transpose, value);
    }

    public void uniformMatrix3x4dv(int location, int count, boolean transpose, Addressable value) {
        GL.programUniformMatrix3x4dv(id(), location, count, transpose, value);
    }

    public void uniformMatrix3x4dv(int location, int count, boolean transpose, double[] value) {
        GL.programUniformMatrix3x4dv(id(), location, count, transpose, value);
    }

    public void uniformMatrix3x4dv(int location, boolean transpose, double[] value) {
        GL.programUniformMatrix3x4dv(id(), location, transpose, value);
    }

    public void uniformMatrix3x4fv(int location, int count, boolean transpose, Addressable value) {
        GL.programUniformMatrix3x4fv(id(), location, count, transpose, value);
    }

    public void uniformMatrix3x4fv(int location, int count, boolean transpose, float[] value) {
        GL.programUniformMatrix3x4fv(id(), location, count, transpose, value);
    }

    public void uniformMatrix3x4fv(int location, boolean transpose, float[] value) {
        GL.programUniformMatrix3x4fv(id(), location, transpose, value);
    }

    public void uniformMatrix4dv(int location, int count, boolean transpose, Addressable value) {
        GL.programUniformMatrix4dv(id(), location, count, transpose, value);
    }

    public void uniformMatrix4dv(int location, int count, boolean transpose, double[] value) {
        GL.programUniformMatrix4dv(id(), location, count, transpose, value);
    }

    public void uniformMatrix4dv(int location, boolean transpose, double[] value) {
        GL.programUniformMatrix4dv(id(), location, transpose, value);
    }

    public void uniformMatrix4fv(int location, int count, boolean transpose, Addressable value) {
        GL.programUniformMatrix4fv(id(), location, count, transpose, value);
    }

    public void uniformMatrix4fv(int location, int count, boolean transpose, float[] value) {
        GL.programUniformMatrix4fv(id(), location, count, transpose, value);
    }

    public void uniformMatrix4fv(int location, boolean transpose, float[] value) {
        GL.programUniformMatrix4fv(id(), location, transpose, value);
    }

    public void uniformMatrix4x2dv(int location, int count, boolean transpose, Addressable value) {
        GL.programUniformMatrix4x2dv(id(), location, count, transpose, value);
    }

    public void uniformMatrix4x2dv(int location, int count, boolean transpose, double[] value) {
        GL.programUniformMatrix4x2dv(id(), location, count, transpose, value);
    }

    public void uniformMatrix4x2dv(int location, boolean transpose, double[] value) {
        GL.programUniformMatrix4x2dv(id(), location, transpose, value);
    }

    public void uniformMatrix4x2fv(int location, int count, boolean transpose, Addressable value) {
        GL.programUniformMatrix4x2fv(id(), location, count, transpose, value);
    }

    public void uniformMatrix4x2fv(int location, int count, boolean transpose, float[] value) {
        GL.programUniformMatrix4x2fv(id(), location, count, transpose, value);
    }

    public void uniformMatrix4x2fv(int location, boolean transpose, float[] value) {
        GL.programUniformMatrix4x2fv(id(), location, transpose, value);
    }

    public void uniformMatrix4x3dv(int location, int count, boolean transpose, Addressable value) {
        GL.programUniformMatrix4x3dv(id(), location, count, transpose, value);
    }

    public void uniformMatrix4x3dv(int location, int count, boolean transpose, double[] value) {
        GL.programUniformMatrix4x3dv(id(), location, count, transpose, value);
    }

    public void uniformMatrix4x3dv(int location, boolean transpose, double[] value) {
        GL.programUniformMatrix4x3dv(id(), location, transpose, value);
    }

    public void uniformMatrix4x3fv(int location, int count, boolean transpose, Addressable value) {
        GL.programUniformMatrix4x3fv(id(), location, count, transpose, value);
    }

    public void uniformMatrix4x3fv(int location, int count, boolean transpose, float[] value) {
        GL.programUniformMatrix4x3fv(id(), location, count, transpose, value);
    }

    public void uniformMatrix4x3fv(int location, boolean transpose, float[] value) {
        GL.programUniformMatrix4x3fv(id(), location, transpose, value);
    }

    public void getActiveAtomicCounterBufferiv(int bufferIndex, int pname, Addressable params) {
        GL.getActiveAtomicCounterBufferiv(id(), bufferIndex, pname, params);
    }

    public void getActiveAtomicCounterBufferiv(int bufferIndex, int pname, int[] params) {
        GL.getActiveAtomicCounterBufferiv(id(), bufferIndex, pname, params);
    }

    public int getActiveAtomicCounterBufferi(int bufferIndex, int pname) {
        return GL.getActiveAtomicCounterBufferi(id(), bufferIndex, pname);
    }

    public void getInterfaceiv(int programInterface, int pname, Addressable params) {
        GL.getProgramInterfaceiv(id(), programInterface, pname, params);
    }

    public int getInterfacei(int programInterface, int pname) {
        return GL.getProgramInterfacei(id(), programInterface, pname);
    }

    public int getResourceIndex(int programInterface, Addressable name) {
        return GL.getProgramResourceIndex(id(), programInterface, name);
    }

    public int getResourceIndex(int programInterface, String name) {
        return GL.getProgramResourceIndex(id(), programInterface, name);
    }

    public int getResourceLocation(int programInterface, Addressable name) {
        return GL.getProgramResourceLocation(id(), programInterface, name);
    }

    public int getResourceLocation(int programInterface, String name) {
        return GL.getProgramResourceLocation(id(), programInterface, name);
    }

    public int getResourceLocationIndex(int programInterface, Addressable name) {
        return GL.getProgramResourceLocationIndex(id(), programInterface, name);
    }

    public int getResourceLocationIndex(int programInterface, String name) {
        return GL.getProgramResourceLocationIndex(id(), programInterface, name);
    }

    public void getResourceName(int programInterface, int index, int bufSize, Addressable length, Addressable name) {
        GL.getProgramResourceName(id(), programInterface, index, bufSize, length, name);
    }

    public void getResourceName(int programInterface, int index, Addressable length, MemorySegment name) {
        GL.getProgramResourceName(id(), programInterface, index, length, name);
    }

    public String getResourceName(int programInterface, int index, int bufSize, int @Nullable [] length) {
        return GL.getProgramResourceName(id(), programInterface, index, bufSize, length);
    }

    public String getResourceName(int programInterface, int index, int @Nullable [] length) {
        return GL.getProgramResourceName(id(), programInterface, index, length);
    }

    public void getResourceiv(int programInterface, int index, int propCount, Addressable props, int count, Addressable length, Addressable params) {
        GL.getProgramResourceiv(id(), programInterface, index, propCount, props, count, length, params);
    }

    public void getResourceiv(int programInterface, int index, MemorySegment props, Addressable length, MemorySegment params) {
        GL.getProgramResourceiv(id(), programInterface, index, props, length, params);
    }

    public void getResourceiv(int programInterface, int index, int[] props, int @Nullable [] length, int[] params) {
        GL.getProgramResourceiv(id(), programInterface, index, props, length, params);
    }

    public int getResourceiv(int programInterface, int index, int[] props) {
        return GL.getProgramResourceiv(id(), programInterface, index, props);
    }

    public void shaderStorageBlockBinding(int storageBlockIndex, int storageBlockBinding) {
        GL.shaderStorageBlockBinding(id(), storageBlockIndex, storageBlockBinding);
    }

    public void getnUniformdv(int location, int bufSize, Addressable params) {
        GL.getnUniformdv(id(), location, bufSize, params);
    }

    public void getnUniformdv(int location, MemorySegment params) {
        GL.getnUniformdv(id(), location, params);
    }

    public void getnUniformdv(int location, double[] params) {
        GL.getnUniformdv(id(), location, params);
    }

    public void getnUniformfv(int location, int bufSize, Addressable params) {
        GL.getnUniformfv(id(), location, bufSize, params);
    }

    public void getnUniformfv(int location, MemorySegment params) {
        GL.getnUniformfv(id(), location, params);
    }

    public void getnUniformfv(int location, float[] params) {
        GL.getnUniformfv(id(), location, params);
    }

    public void getnUniformiv(int location, int bufSize, Addressable params) {
        GL.getnUniformiv(id(), location, bufSize, params);
    }

    public void getnUniformiv(int location, MemorySegment params) {
        GL.getnUniformiv(id(), location, params);
    }

    public void getnUniformiv(int location, int[] params) {
        GL.getnUniformiv(id(), location, params);
    }

    public void getnUniformuiv(int location, int bufSize, Addressable params) {
        GL.getnUniformuiv(id(), location, bufSize, params);
    }

    public void getnUniformuiv(int location, MemorySegment params) {
        GL.getnUniformuiv(id(), location, params);
    }

    public void getnUniformuiv(int location, int[] params) {
        GL.getnUniformuiv(id(), location, params);
    }

    /**
     * Gets the id of this OpenGL program.
     *
     * @return the id of this OpenGL program
     */
    public int id() {
        return id;
    }
}
