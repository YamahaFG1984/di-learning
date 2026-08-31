/**
 * Copyright (C) 2009 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package screen;

import java.io.InputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class Thumbnail {
  private static final Logger logger = Logger.getLogger(Thumbnail.class.getName());

    private final FileSystem fileSystem;
    private InputStream data;

    public Thumbnail(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    public Image display(String path) throws IOException {
         data = fileSystem.getPath(path).newInputStream();

      return null;
    }

  private interface Foo {
    boolean isOpen();
    void someLastMinuteLogic();
  }

  Foo reader;

  public void close() {
      if (null != reader) {
          if (reader.isOpen())
              reader.someLastMinuteLogic();

          reader = null;
      }

  }

  @Override
  protected void finalize() throws Throwable {
      if (null != data) {
        logger.warning("unclosed thumbnail: " + this);
        data.close();
      }

      data = null;
      super.finalize();
  }
}
