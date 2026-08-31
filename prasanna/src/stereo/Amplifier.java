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


package stereo;

import equipment.Synthesizer;
import equipment.Footpedal;
import equipment.Speaker;
import equipment.Guitar;
import com.google.inject.Inject;

public class Amplifier {
	private Guitar guitar;
	private Speaker speaker;
	private Footpedal footpedal;
	private Synthesizer synthesizer;

  @Inject
 void prepareAmp(Guitar guitar, Speaker speaker, Footpedal footpedal,
             Synthesizer synthesizer) {
   this.guitar = guitar;
   this.speaker = speaker;
   this.footpedal = footpedal;
   this.synthesizer = synthesizer;
 }
}
  