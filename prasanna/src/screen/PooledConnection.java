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

import static screen.PooledConnection.ConnectionState.IN_USE;
import static screen.PooledConnection.ConnectionState.IDLE;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class PooledConnection {
    private Connection conn;
    private ConnectionState state;

    public synchronized void open() throws SQLException {
        conn = DriverManager.getConnection(null);
    }

    public synchronized void onCheckout() {
        this.state = IN_USE;
    }

    public synchronized void onReturn() {
        this.state = IDLE;
    }

public synchronized void close() throws SQLException{
        if (IN_USE == state)
            throw new IllegalStateException();

        conn.close();
    }

  enum ConnectionState {
    IDLE,
    IN_USE
  }
}
