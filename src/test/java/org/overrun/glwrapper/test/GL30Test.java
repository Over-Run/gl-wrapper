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

package org.overrun.glwrapper.test;

import org.overrun.glib.gl.GL;
import org.overrun.glib.gl.GL41C;
import org.overrun.glib.gl.GLCaps;
import org.overrun.glib.glfw.GLFW;
import org.overrun.glib.glfw.GLFWErrorCallback;
import org.overrun.glib.stb.STBImage;
import org.overrun.glwrapper.GLFWWindow;
import org.overrun.glwrapper.GLProgram;
import org.overrun.glwrapper.GLShader;

import java.io.IOException;
import java.lang.foreign.MemoryAddress;
import java.util.Objects;

import static org.overrun.glib.gl.GLConstC.*;

/**
 * Tests OpenGL 3.0 vertex arrays
 *
 * @author squid233
 * @since 0.1.0
 */
public final class GL30Test {
    private GLFWWindow window;

    public void run() {
        init();
        loop();

        window.destroy();

        GLFW.terminate();
        GLFW.setErrorCallback(null);
    }

    private void init() {
        GLFWErrorCallback.createPrint().set();
        if (!GLFW.init()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        window = GLFWWindow.defaultHints()
            .hint(GLFW.VISIBLE, false)
            .hint(GLFW.RESIZABLE, true)
            .create(640, 480, "OpenGL 3.0", MemoryAddress.NULL, MemoryAddress.NULL)
            .check();
        window.setKeyCallback((handle, key, scancode, action, mods) -> {
            if (key == GLFW.KEY_ESCAPE && action == GLFW.RELEASE) {
                window.setShouldClose(true);
            }
        });
        window.setFramebufferSizeCallback((handle, width, height) ->
            GL.viewport(0, 0, width, height));
        var vidMode = GLFW.getVideoMode(GLFW.getPrimaryMonitor());
        if (vidMode != null) {
            var size = window.getSize();
            window.setPos(
                (vidMode.width() - size.x()) / 2,
                (vidMode.height() - size.y()) / 2
            );
        }

        window.makeContextCurrent();
        GLFW.swapInterval(1);

        window.show();
    }

    private void loop() {
        if (GLCaps.loadShared(true, GLFW::getProcAddress) == 0)
            throw new IllegalStateException("Failed to load OpenGL");

        GL.clearColor(0.4f, 0.6f, 0.9f, 1.0f);

        int tex = GL.genTexture();
        GL.bindTexture(GL_TEXTURE_2D, tex);
        GL.texParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        GL.texParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        try (var is = ClassLoader.getSystemResourceAsStream("image.png")) {
            byte[] bytes = Objects.requireNonNull(is).readNBytes(256);
            int[] px = new int[1], py = new int[1], pc = new int[1];
            var data = STBImage.loadFromMemory(bytes, px, py, pc, STBImage.RGB);
            GL.texImage2D(GL_TEXTURE_2D,
                0,
                GL_RGB,
                px[0],
                py[0],
                0,
                GL_RGB,
                GL_UNSIGNED_BYTE,
                data);
            STBImage.free(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GL.bindTexture(GL_TEXTURE_2D, 0);

        var program = new GLProgram();
        var vsh = new GLShader(GL_VERTEX_SHADER);
        var fsh = new GLShader(GL_FRAGMENT_SHADER);
        vsh.source("""
            #version 130

            in vec3 position;
            in vec2 uv;

            out vec2 texCoord;

            void main() {
                gl_Position = vec4(position, 1.0);
                texCoord = uv;
            }
            """);
        fsh.source("""
            #version 130

            in vec2 texCoord;

            out vec4 fragColor;

            uniform sampler2D sampler;
            uniform float colorFactor;

            void main() {
                fragColor = colorFactor * texture(sampler, texCoord);
            }
            """);
        vsh.compile();
        fsh.compile();
        program.attachShader(vsh);
        program.attachShader(fsh);
        program.bindAttribLocation(0, "position");
        program.bindAttribLocation(1, "uv");
        program.link();
        program.detachShader(vsh);
        program.detachShader(fsh);
        vsh.delete();
        fsh.delete();
        program.use();
        GL.uniform1i(program.getUniformLocation("sampler"), 0);
        GL.useProgram(0);

        int vao = GL.genVertexArray();
        GL.bindVertexArray(vao);
        int vbo = GL.genBuffer();
        GL.bindBuffer(GL_ARRAY_BUFFER, vbo);
        GL.bufferData(GL_ARRAY_BUFFER, new float[]{
            // Vertex          UV
            -0.5f, 0.5f, 0.0f, 0.0f, 0.0f,
            -0.5f, -0.5f, 0.0f, 0.0f, 1.0f,
            0.5f, -0.5f, 0.0f, 1.0f, 1.0f,
            0.5f, 0.5f, 0.0f, 1.0f, 0.0f
        }, GL_STATIC_DRAW);
        int ebo = GL.genBuffer();
        GL.bindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
        GL.bufferData(GL_ELEMENT_ARRAY_BUFFER, new int[]{
            0, 1, 2, 0, 2, 3
        }, GL_STATIC_DRAW);
        GL.enableVertexAttribArray(0);
        GL.enableVertexAttribArray(1);
        GL.vertexAttribPointer(0, 3, GL_FLOAT, false, 20, MemoryAddress.NULL);
        GL.vertexAttribPointer(1, 2, GL_FLOAT, false, 20, MemoryAddress.ofLong(12));
        GL.bindBuffer(GL_ARRAY_BUFFER, 0);
        GL.bindVertexArray(0);

        final int colorFactor = program.getUniformLocation("colorFactor");

        while (!window.shouldClose()) {
            GL.clear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            // Draw triangle
            GL.bindTexture(GL_TEXTURE_2D, tex);
            program.use();
            float color = (float) ((Math.sin(GLFW.getTime() * 2) + 1 * 0.5) * 0.6 + 0.4);
            if (GL41C.glUniform1f != null)
                program.uniform1f(colorFactor, color);
            else
                GL.uniform1f(colorFactor, color);
            GL.bindVertexArray(vao);
            GL.drawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, MemoryAddress.NULL);
            GL.bindVertexArray(0);
            GL.useProgram(0);
            GL.bindTexture(GL_TEXTURE_2D, 0);

            window.swapBuffers();

            GLFW.pollEvents();
        }

        program.delete();
        GL.deleteVertexArray(vao);
        GL.deleteBuffer(vbo);
        GL.deleteTexture(tex);
    }

    public static void main(String[] args) {
        new GL30Test().run();
    }
}
