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


package c9;

import java.util.ArrayList;

public class Queue<I> {
    private int end = -1;
    private final ArrayList<I> list;

    public Queue(ArrayList<I> list) {
        this.list = list;
    }

    public void enqueue(I item) {
        list.add(0, item);
        end++;
    }

    public I dequeue() {
        if (end == -1) return null;

        end--;
        return list.get(end + 1);
    }

    public void clear() {
        end = -1;

        list.clear();
    }

  public static void main(String[] args) {
    Queue<String> q = new Queue<String>(new ArrayList<String>());
    q.enqueue("Muse");
    q.enqueue("Pearl Jam");
    q.clear();
    q.enqueue("Nirvana");

    System.out.println(q.dequeue());
    System.out.println(null == q.dequeue());
  }
}
