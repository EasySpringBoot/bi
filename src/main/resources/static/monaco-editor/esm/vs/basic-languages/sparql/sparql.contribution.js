/*!-----------------------------------------------------------------------------
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Version: 0.33.0(4b1abad427e58dbedc1215d99a0902ffc885fcd4)
 * Released under the MIT license
 * https://github.com/microsoft/monaco-editor/blob/main/LICENSE.txt
 *-----------------------------------------------------------------------------*/

// src/basic-languages/sparql/sparql.contribution.ts
import { registerLanguage } from "../_.contribution.js";
registerLanguage({
  id: "sparql",
  extensions: [".rq"],
  aliases: ["sparql", "SPARQL"],
  loader: () => {
    if (false) {
      return new Promise((resolve, reject) => {
        __require(["vs/basic-languages/sparql/sparql"], resolve, reject);
      });
    } else {
      return import("./sparql.js");
    }
  }
});
