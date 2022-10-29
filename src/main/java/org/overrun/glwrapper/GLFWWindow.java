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
import org.overrun.glib.glfw.*;
import org.overrun.glib.util.ValueDouble2;
import org.overrun.glib.util.ValueFloat2;
import org.overrun.glib.util.ValueInt2;
import org.overrun.glib.util.ValueInt4;

import java.lang.foreign.Addressable;
import java.lang.foreign.MemoryAddress;
import java.util.function.Supplier;

/**
 * The GLFW window object.
 *
 * @author squid233
 * @since 0.1.0
 */
public final class GLFWWindow {
    private final MemoryAddress handle;
    private String title;
    private final MemoryAddress share;

    /**
     * Creates a GLFW window and its associated context with the passed parameters.
     *
     * @param width   The desired width, in screen coordinates, of the window.
     *                This must be greater than zero.
     * @param height  The desired height, in screen coordinates, of the window.
     *                This must be greater than zero.
     * @param title   The initial, UTF-8 encoded window title.
     * @param monitor The monitor to use for full screen mode, or {@link MemoryAddress#NULL NULL} for
     *                windowed mode.
     * @param share   The window whose context to share resources with, or {@link MemoryAddress#NULL NULL}
     *                to not share resources.
     * @see GLFW#ncreateWindow
     */
    public GLFWWindow(int width, int height,
                      String title,
                      MemoryAddress monitor, MemoryAddress share) {
        this.title = title;
        this.share = share;
        handle = GLFW.createWindow(width, height, title, monitor, share);
    }

    /**
     * Resets all window hints to their default values, and then gets the builder.
     *
     * @return the builder
     * @see #currentHints()
     */
    public static Builder defaultHints() {
        GLFW.defaultWindowHints();
        return new Builder();
    }

    /**
     * Gets the builder.
     *
     * @return the builder
     * @see #defaultHints()
     */
    public static Builder currentHints() {
        return new Builder();
    }

    /**
     * The GLFW window object builder.
     *
     * @author squid233
     * @since 0.1.0
     */
    public static final class Builder {
        /**
         * Sets the specified window hint to the desired value.
         *
         * @param hint  The <a href="https://www.glfw.org/docs/latest/window_guide.html#window_hints">window hint</a> to set.
         * @param value The new value of the window hint.
         * @return this
         */
        public Builder hint(int hint, int value) {
            GLFW.windowHint(hint, value);
            return this;
        }

        /**
         * Sets the specified window hint to the desired value.
         *
         * @param hint  The <a href="https://www.glfw.org/docs/latest/window_guide.html#window_hints">window hint</a> to set.
         * @param value The new value of the window hint.
         * @return this
         */
        public Builder hint(int hint, boolean value) {
            GLFW.windowHint(hint, value);
            return this;
        }

        /**
         * Sets the specified window hint to the desired value.
         *
         * @param hint  The <a href="https://www.glfw.org/docs/latest/window_guide.html#window_hints">window hint</a> to set.
         * @param value The new value of the window hint.
         * @return this
         */
        public Builder nhintString(int hint, Addressable value) {
            GLFW.nwindowHintString(hint, value);
            return this;
        }

        /**
         * Sets the specified window hint to the desired value.
         *
         * @param hint  The <a href="https://www.glfw.org/docs/latest/window_guide.html#window_hints">window hint</a> to set.
         * @param value The new value of the window hint.
         * @return this
         */
        public Builder hintString(int hint, String value) {
            GLFW.windowHintString(hint, value);
            return this;
        }

        /**
         * Creates a GLFW window and its associated context with the passed parameters.
         *
         * @param width   The desired width, in screen coordinates, of the window.
         *                This must be greater than zero.
         * @param height  The desired height, in screen coordinates, of the window.
         *                This must be greater than zero.
         * @param title   The initial, UTF-8 encoded window title.
         * @param monitor The monitor to use for full screen mode, or {@link MemoryAddress#NULL NULL} for
         *                windowed mode.
         * @param share   The window whose context to share resources with, or {@link MemoryAddress#NULL NULL}
         *                to not share resources.
         * @return the created window
         * @see GLFW#ncreateWindow
         */
        public GLFWWindow create(int width, int height,
                                 String title,
                                 MemoryAddress monitor, MemoryAddress share) {
            return new GLFWWindow(width, height, title, monitor, share);
        }
    }

    /**
     * Check the window address.
     *
     * @return this, or fail if the window is {@link MemoryAddress#NULL NULL}
     */
    public GLFWWindow check() {
        return check("Failed to create the GLFW window");
    }

    /**
     * Check the window address.
     *
     * @param message detail message to be used in the event that a {@code IllegalStateException} is thrown
     * @return this, or fail if the window is {@link MemoryAddress#NULL NULL}
     */
    public GLFWWindow check(String message) {
        if (handle == MemoryAddress.NULL) {
            throw new IllegalStateException(message);
        }
        return this;
    }

    /**
     * Check the window address.
     *
     * @param messageSupplier supplier of the detail message to be used in the event that a {@code IllegalStateException} is thrown
     * @return this, or fail if the window is {@link MemoryAddress#NULL NULL}
     */
    public GLFWWindow check(Supplier<String> messageSupplier) {
        if (messageSupplier == null)
            return check();
        return check(messageSupplier.get());
    }

    /**
     * Free the memory session for the given window.
     * <p>
     * Destroys the specified window and its context.
     */
    public void destroy() {
        Callbacks.free(handle);
        GLFW.destroyWindow(handle);
    }

    /**
     * Checks the close flag of the specified window.
     *
     * @return The value of the close flag.
     */
    public boolean shouldClose() {
        return GLFW.windowShouldClose(handle);
    }

    /**
     * Sets the close flag of the specified window.
     *
     * @param value The new value.
     */
    public void setShouldClose(boolean value) {
        GLFW.setWindowShouldClose(handle, value);
    }

    /**
     * Sets the title of the specified window.
     *
     * @param title The UTF-8 encoded window title.
     */
    public void nsetTitle(Addressable title) {
        this.title = title.address().getUtf8String(0);
        GLFW.nsetWindowTitle(handle, title);
    }

    /**
     * Sets the title of the specified window.
     *
     * @param title The UTF-8 encoded window title.
     */
    public void setTitle(String title) {
        this.title = title;
        GLFW.setWindowTitle(handle, title);
    }

    /**
     * Sets the icon for the specified window.
     *
     * @param count  The number of images in the specified array, or zero to
     *               revert to the default window icon.
     * @param images The images to create the icon from.  This is ignored if
     *               count is zero.
     */
    public void nsetIcon(int count, Addressable images) {
        GLFW.nsetWindowIcon(handle, count, images);
    }

    /**
     * Sets the icon for the specified window.
     *
     * @param count  The number of images in the specified array, or zero to
     *               revert to the default window icon.
     * @param images The images to create the icon from.  This is ignored if
     *               count is zero.
     */
    public void setIcon(int count, GLFWImage.Buffer images) {
        GLFW.setWindowIcon(handle, count, images);
    }

    /**
     * Sets the icon for the specified window.
     *
     * @param images The images to create the icon from.  This is ignored if
     *               count is zero.
     */
    public void setIcon(GLFWImage.Buffer images) {
        GLFW.setWindowIcon(handle, images);
    }

    /**
     * Retrieves the position of the content area of the specified window.
     *
     * @return the xy-coordinate of the upper-left corner of the content area.
     */
    public ValueInt2 getPos() {
        return GLFW.getWindowPos(handle);
    }

    /**
     * Sets the position of the content area of the specified window.
     *
     * @param xpos The x-coordinate of the upper-left corner of the content area.
     * @param ypos The y-coordinate of the upper-left corner of the content area.
     */
    public void setPos(int xpos, int ypos) {
        GLFW.setWindowPos(handle, xpos, ypos);
    }

    /**
     * Retrieves the size of the content area of the specified window.
     *
     * @return the width and height, in screen coordinates, of the content area.
     */
    public ValueInt2 getSize() {
        return GLFW.getWindowSize(handle);
    }

    /**
     * Sets the size limits of the specified window.
     *
     * @param minWidth  The minimum width, in screen coordinates, of the content
     *                  area, or {@link GLFW#DONT_CARE}.
     * @param minHeight The minimum height, in screen coordinates, of the
     *                  content area, or {@link GLFW#DONT_CARE}.
     * @param maxWidth  The maximum width, in screen coordinates, of the content
     *                  area, or {@link GLFW#DONT_CARE}.
     * @param maxHeight The maximum height, in screen coordinates, of the
     *                  content area, or {@link GLFW#DONT_CARE}.
     */
    public void setSizeLimits(int minWidth, int minHeight, int maxWidth, int maxHeight) {
        GLFW.setWindowSizeLimits(handle, minWidth, minHeight, maxWidth, maxHeight);
    }

    /**
     * Sets the aspect ratio of the specified window.
     *
     * @param numer The numerator of the desired aspect ratio, or
     *              {@link GLFW#DONT_CARE}.
     * @param denom The denominator of the desired aspect ratio, or
     *              {@link GLFW#DONT_CARE}.
     */
    public void setAspectRatio(int numer, int denom) {
        GLFW.setWindowAspectRatio(handle, numer, denom);
    }

    /**
     * Sets the size of the content area of the specified window.
     *
     * @param width  The desired width, in screen coordinates, of the window
     *               content area.
     * @param height The desired height, in screen coordinates, of the window
     *               content area.
     */
    public void setSize(int width, int height) {
        GLFW.setWindowSize(handle, width, height);
    }

    /**
     * Retrieves the size of the framebuffer of the specified window.
     *
     * @return the width and height, in pixels, of the framebuffer.
     */
    public ValueInt2 getFramebufferSize() {
        return GLFW.getFramebufferSize(handle);
    }

    /**
     * Retrieves the size of the frame of the window.
     *
     * @return the size, in screen coordinates, of the left, top, right and bottom
     * edge of the window frame.
     */
    public ValueInt4 getFrameSize() {
        return GLFW.getWindowFrameSize(handle);
    }

    /**
     * Retrieves the content scale for the specified window.
     *
     * @return the xy-axis content scale.
     */
    public ValueFloat2 getContentScale() {
        return GLFW.getWindowContentScale(handle);
    }

    /**
     * Returns the opacity of the whole window.
     *
     * @return The opacity value of the specified window.
     */
    public float getOpacity() {
        return GLFW.getWindowOpacity(handle);
    }

    /**
     * Sets the opacity of the whole window.
     *
     * @param opacity The desired opacity of the specified window.
     */
    public void setOpacity(float opacity) {
        GLFW.setWindowOpacity(handle, opacity);
    }

    /**
     * Iconifies the specified window.
     */
    public void iconify() {
        GLFW.iconifyWindow(handle);
    }

    /**
     * Restores the specified window.
     */
    public void restore() {
        GLFW.restoreWindow(handle);
    }

    /**
     * Maximizes the specified window.
     */
    public void maximize() {
        GLFW.maximizeWindow(handle);
    }

    /**
     * Makes the specified window visible.
     */
    public void show() {
        GLFW.showWindow(handle);
    }

    /**
     * Hides the specified window.
     */
    public void hide() {
        GLFW.hideWindow(handle);
    }

    /**
     * Brings the specified window to front and sets input focus.
     */
    public void focus() {
        GLFW.focusWindow(handle);
    }

    /**
     * Requests user attention to the specified window.
     */
    public void requestAttention() {
        GLFW.requestWindowAttention(handle);
    }

    /**
     * Returns the monitor that the window uses for full screen mode.
     *
     * @return The monitor, or {@link MemoryAddress#NULL NULL} if the window is in windowed mode or an
     * <a href="https://www.glfw.org/docs/latest/intro_guide.html#error_handling">error</a> occurred.
     */
    public MemoryAddress getMonitor() {
        return GLFW.getWindowMonitor(handle);
    }

    /**
     * Sets the mode, monitor, video mode and placement of a window.
     *
     * @param monitor     The desired monitor, or {@link MemoryAddress#NULL NULL} to set windowed mode.
     * @param xpos        The desired x-coordinate of the upper-left corner of the
     *                    content area.
     * @param ypos        The desired y-coordinate of the upper-left corner of the
     *                    content area.
     * @param width       The desired with, in screen coordinates, of the content
     *                    area or video mode.
     * @param height      The desired height, in screen coordinates, of the content
     *                    area or video mode.
     * @param refreshRate The desired refresh rate, in Hz, of the video mode,
     *                    or {@link GLFW#DONT_CARE}.
     */
    public void setMonitor(Addressable monitor, int xpos, int ypos, int width, int height, int refreshRate) {
        GLFW.setWindowMonitor(handle, monitor, xpos, ypos, width, height, refreshRate);
    }

    /**
     * Returns an attribute of the specified window.
     *
     * @param attrib The <a href="https://www.glfw.org/docs/latest/window_guide.html#window_attribs">window attribute</a>
     *               whose value to return.
     * @return The value of the attribute, or zero if an
     * <a href="https://www.glfw.org/docs/latest/intro_guide.html#error_handling">error</a> occurred.
     */
    public int getAttrib(int attrib) {
        return GLFW.getWindowAttrib(handle, attrib);
    }

    /**
     * Sets an attribute of the specified window.
     *
     * @param attrib A supported window attribute.
     * @param value  {@code true} of {@code false}.
     */
    public void setAttrib(int attrib, boolean value) {
        GLFW.setWindowAttrib(handle, attrib, value);
    }

    /**
     * Sets the user pointer of the specified window.
     *
     * @param pointer The new value.
     */
    public void setUserPointer(Addressable pointer) {
        GLFW.setWindowUserPointer(handle, pointer);
    }

    /**
     * Returns the user pointer of the specified window.
     *
     * @return the user pointer of the specified window.
     */
    public MemoryAddress getUserPointer() {
        return GLFW.getWindowUserPointer(handle);
    }

    /**
     * Sets the position callback for the specified window.
     *
     * @param callback The new callback, or {@code null} to remove the currently set
     *                 callback.
     * @return The previously set callback, or {@link MemoryAddress#NULL NULL} if no callback was set or the
     * library had not been <a href="https://www.glfw.org/docs/latest/intro_guide.html#intro_init">initialized</a>.
     */
    public MemoryAddress setPosCallback(@Nullable IGLFWWindowPosFun callback) {
        return GLFW.setWindowPosCallback(handle, callback);
    }

    /**
     * Sets the size callback for the specified window.
     *
     * @param callback The new callback, or {@code null} to remove the currently set
     *                 callback.
     * @return The previously set callback, or {@link MemoryAddress#NULL NULL} if no callback was set or the
     * library had not been <a href="https://www.glfw.org/docs/latest/intro_guide.html#intro_init">initialized</a>.
     */
    public MemoryAddress setSizeCallback(@Nullable IGLFWWindowSizeFun callback) {
        return GLFW.setWindowSizeCallback(handle, callback);
    }

    /**
     * Sets the close callback for the specified window.
     *
     * @param callback The new callback, or {@code null} to remove the currently set
     *                 callback.
     * @return The previously set callback, or {@link MemoryAddress#NULL NULL} if no callback was set or the
     * library had not been <a href="https://www.glfw.org/docs/latest/intro_guide.html#intro_init">initialized</a>.
     */
    public MemoryAddress setCloseCallback(@Nullable IGLFWWindowCloseFun callback) {
        return GLFW.setWindowCloseCallback(handle, callback);
    }

    /**
     * Sets the refresh callback for the specified window.
     *
     * @param callback The new callback, or {@code null} to remove the currently set
     *                 callback.
     * @return The previously set callback, or {@link MemoryAddress#NULL NULL} if no callback was set or the
     * library had not been <a href="https://www.glfw.org/docs/latest/intro_guide.html#intro_init">initialized</a>.
     */
    public MemoryAddress setRefreshCallback(@Nullable IGLFWWindowRefreshFun callback) {
        return GLFW.setWindowRefreshCallback(handle, callback);
    }

    /**
     * Sets the focus callback for the specified window.
     *
     * @param callback The new callback, or {@code null} to remove the currently set
     *                 callback.
     * @return The previously set callback, or {@link MemoryAddress#NULL NULL} if no callback was set or the
     * library had not been <a href="https://www.glfw.org/docs/latest/intro_guide.html#intro_init">initialized</a>.
     */
    public MemoryAddress setFocusCallback(@Nullable IGLFWWindowFocusFun callback) {
        return GLFW.setWindowFocusCallback(handle, callback);
    }

    /**
     * Sets the iconify callback for the specified window.
     *
     * @param callback The new callback, or {@code null} to remove the currently set
     *                 callback.
     * @return The previously set callback, or {@link MemoryAddress#NULL NULL} if no callback was set or the
     * library had not been <a href="https://www.glfw.org/docs/latest/intro_guide.html#intro_init">initialized</a>.
     */
    public MemoryAddress setIconifyCallback(@Nullable IGLFWWindowIconifyFun callback) {
        return GLFW.setWindowIconifyCallback(handle, callback);
    }

    /**
     * Sets the maximize callback for the specified window.
     *
     * @param callback The new callback, or {@code null} to remove the currently set
     *                 callback.
     * @return The previously set callback, or {@link MemoryAddress#NULL NULL} if no callback was set or the
     * library had not been <a href="https://www.glfw.org/docs/latest/intro_guide.html#intro_init">initialized</a>.
     */
    public MemoryAddress setMaximizeCallback(@Nullable IGLFWWindowMaximizeFun callback) {
        return GLFW.setWindowMaximizeCallback(handle, callback);
    }

    /**
     * Sets the framebuffer resize callback for the specified window.
     *
     * @param callback The new callback, or {@code null} to remove the currently set
     *                 callback.
     * @return The previously set callback, or {@link MemoryAddress#NULL NULL} if no callback was set or the
     * library had not been <a href="https://www.glfw.org/docs/latest/intro_guide.html#intro_init">initialized</a>.
     */
    public MemoryAddress setFramebufferSizeCallback(@Nullable IGLFWFramebufferSizeFun callback) {
        return GLFW.setFramebufferSizeCallback(handle, callback);
    }

    /**
     * Sets the window content scale callback for the specified window.
     *
     * @param callback The new callback, or {@code null} to remove the currently set
     *                 callback.
     * @return The previously set callback, or {@link MemoryAddress#NULL NULL} if no callback was set or the
     * library had not been <a href="https://www.glfw.org/docs/latest/intro_guide.html#intro_init">initialized</a>.
     */
    public MemoryAddress setContentScaleCallback(@Nullable IGLFWWindowContentScaleFun callback) {
        return GLFW.setWindowContentScaleCallback(handle, callback);
    }

    /**
     * Returns the value of an input option for the specified window.
     *
     * @param mode One of {@code CURSOR}, {@code STICKY_KEYS},
     *             {@code STICKY_MOUSE_BUTTONS}, {@code LOCK_KEY_MODS} or
     *             {@code RAW_MOUSE_MOTION}.
     * @return the value of an input option for the specified window.
     */
    public int getInputMode(int mode) {
        return GLFW.getInputMode(handle, mode);
    }

    /**
     * Sets an input option for the specified window.
     *
     * @param mode  One of {@code CURSOR}, {@code STICKY_KEYS},
     *              {@code STICKY_MOUSE_BUTTONS}, {@code LOCK_KEY_MODS} or
     *              {@code RAW_MOUSE_MOTION}.
     * @param value The new value of the specified input mode.
     */
    public void setInputMode(int mode, int value) {
        GLFW.setInputMode(handle, mode, value);
    }

    /**
     * Returns the last reported state of a keyboard key for the specified
     * window.
     *
     * @param key The desired <a href="https://www.glfw.org/docs/latest/group__keys.html">keyboard key</a>.
     *            {@code KEY_UNKNOWN} is not a valid key for this function.
     * @return One of {@code PRESS} or {@code RELEASE}.
     */
    public int getKey(int key) {
        return GLFW.getKey(handle, key);
    }

    /**
     * Returns the last reported state of a mouse button for the specified
     * window.
     *
     * @param button The desired <a href="https://www.glfw.org/docs/latest/group__buttons.html">mouse button</a>.
     * @return One of {@code PRESS} or {@code RELEASE}.
     */
    public int getMouseButton(int button) {
        return GLFW.getMouseButton(handle, button);
    }

    /**
     * Retrieves the position of the cursor relative to the content area of
     * the window.
     *
     * @return the cursor xy-coordinate, relative to the left and top edge of the content area.
     */
    public ValueDouble2 getCursorPos() {
        return GLFW.getCursorPos(handle);
    }

    /**
     * Sets the position of the cursor, relative to the content area of the
     * window.
     *
     * @param xpos The desired x-coordinate, relative to the left edge of the
     *             content area.
     * @param ypos The desired y-coordinate, relative to the top edge of the
     *             content area.
     */
    public void setCursorPos(double xpos, double ypos) {
        GLFW.setCursorPos(handle, xpos, ypos);
    }

    /**
     * Sets the cursor for the window.
     *
     * @param cursor The cursor to set, or {@link MemoryAddress#NULL NULL} to switch
     *               back to the default arrow cursor.
     */
    public void setCursor(Addressable cursor) {
        GLFW.setCursor(handle, cursor);
    }

    /**
     * Sets the key callback.
     *
     * @param callback The new key callback, or {@code null} to remove the currently
     *                 set callback.
     * @return The previously set callback, or {@link MemoryAddress#NULL NULL} if no callback was set or the
     * library had not been <a href="https://www.glfw.org/docs/latest/intro_guide.html#intro_init">initialized</a>.
     */
    public MemoryAddress setKeyCallback(@Nullable IGLFWKeyFun callback) {
        return GLFW.setKeyCallback(handle, callback);
    }

    /**
     * Sets the Unicode character callback.
     *
     * @param callback The new callback, or {@code null} to remove the currently set
     *                 callback.
     * @return The previously set callback, or {@link MemoryAddress#NULL NULL} if no callback was set or the
     * library had not been <a href="https://www.glfw.org/docs/latest/intro_guide.html#intro_init">initialized</a>.
     */
    public MemoryAddress setCharCallback(@Nullable IGLFWCharFun callback) {
        return GLFW.setCharCallback(handle, callback);
    }

    /**
     * Sets the mouse button callback.
     *
     * @param callback The new callback, or {@code null} to remove the currently set
     *                 callback.
     * @return The previously set callback, or {@link MemoryAddress#NULL NULL} if no callback was set or the
     * library had not been <a href="https://www.glfw.org/docs/latest/intro_guide.html#intro_init">initialized</a>.
     */
    public MemoryAddress setMouseButtonCallback(@Nullable IGLFWMouseButtonFun callback) {
        return GLFW.setMouseButtonCallback(handle, callback);
    }

    /**
     * Sets the cursor position callback.
     *
     * @param callback The new callback, or {@code null} to remove the currently set
     *                 callback.
     * @return The previously set callback, or {@link MemoryAddress#NULL NULL} if no callback was set or the
     * library had not been <a href="https://www.glfw.org/docs/latest/intro_guide.html#intro_init">initialized</a>.
     */
    public MemoryAddress setCursorPosCallback(@Nullable IGLFWCursorPosFun callback) {
        return GLFW.setCursorPosCallback(handle, callback);
    }

    /**
     * Sets the cursor enter/leave callback.
     *
     * @param callback The new callback, or {@code null} to remove the currently set
     *                 callback.
     * @return The previously set callback, or {@link MemoryAddress#NULL NULL} if no callback was set or the
     * library had not been <a href="https://www.glfw.org/docs/latest/intro_guide.html#intro_init">initialized</a>.
     */
    public MemoryAddress setCursorEnterCallback(@Nullable IGLFWCursorEnterFun callback) {
        return GLFW.setCursorEnterCallback(handle, callback);
    }

    /**
     * Sets the scroll callback.
     *
     * @param callback The new scroll callback, or {@code null} to remove the
     *                 currently set callback.
     * @return The previously set callback, or {@link MemoryAddress#NULL NULL} if no callback was set or the
     * library had not been <a href="https://www.glfw.org/docs/latest/intro_guide.html#intro_init">initialized</a>.
     */
    public MemoryAddress setScrollCallback(@Nullable IGLFWScrollFun callback) {
        return GLFW.setScrollCallback(handle, callback);
    }

    /**
     * Sets the path drop callback.
     *
     * @param callback The new file drop callback, or {@code null} to remove the
     *                 currently set callback.
     * @return The previously set callback, or {@link MemoryAddress#NULL NULL} if no callback was set or the
     * library had not been <a href="https://www.glfw.org/docs/latest/intro_guide.html#intro_init">initialized</a>.
     */
    public MemoryAddress setDropCallback(@Nullable IGLFWDropFun callback) {
        return GLFW.setDropCallback(handle, callback);
    }

    /**
     * Makes the context of the specified window current for the calling thread.
     */
    public void makeContextCurrent() {
        GLFW.makeContextCurrent(handle);
    }

    /**
     * Swaps the front and back buffers of the specified window.
     */
    public void swapBuffers() {
        GLFW.swapBuffers(handle);
    }

    /**
     * Creates a Vulkan surface for the specified window.
     *
     * @param instance  The Vulkan instance to create the surface in.
     * @param allocator The allocator to use, or {@link MemoryAddress#NULL NULL} to use the default
     *                  allocator.
     * @param surface   Where to store the handle of the surface.  This is set
     *                  to {@code VK_NULL_HANDLE} if an error occurred.
     * @return {@code VK_SUCCESS} if successful, or a Vulkan error code if an
     * <a href="https://www.glfw.org/docs/latest/intro_guide.html#error_handling">error</a> occurred.
     */
    public int createSurface(Addressable instance, Addressable allocator, long[] surface) {
        return GLFWVulkan.glfwCreateWindowSurface(instance, handle, allocator, surface);
    }

    /**
     * Gets the GLFW window handle.
     *
     * @return the window handle.
     */
    public MemoryAddress handle() {
        return handle;
    }

    /**
     * Gets the GLFW window title.
     *
     * @return the window title.
     */
    public String title() {
        return title;
    }

    /**
     * Gets the GLFW window share.
     *
     * @return The window whose context to share resources with, or {@link MemoryAddress#NULL NULL}
     * to not share resources.
     */
    public MemoryAddress share() {
        return share;
    }
}
