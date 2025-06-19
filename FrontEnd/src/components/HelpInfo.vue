<template>
  <div class="help-text">
    <h1>SQL Translator – Help & Notes</h1>

<div class="warning">
  <strong>⚠ Translation Disclaimer:</strong><br>
  SQL translation is not always accurate or complete. You must **review and test** every translated query before using it in production.
</div>

<h2>How It Works</h2>
<p>
  This app lets you translate SQL queries between different dialects using one of two engines:
</p>
<ul>
  <li><strong>SQLGlot</strong> – <em>default engine</em></li>
  <li><strong>Apache Calcite</strong> – <em>optional fallback if Glot doesn't deliver</em></li>
</ul>

<p>
  If SQLGlot fails to produce correct results, try running the same query using Calcite instead. However, translation success depends heavily on syntax, dialects, and supported features.
</p>

<h2>Limitations & Caveats</h2>
<ul>
  <li>SQL dialects can have major differences in <strong>functions, SerDes, syntax</strong>, and <strong>semantics</strong>.</li>
  <li>Trino has <strong>limited or no support</strong> for Hive-specific features like custom SerDes and legacy file formats.</li>
  <li>Some functions and syntactic constructs may not be available or behave differently between Hive and Trino.</li>
  <li><strong>Manual tweaking</strong> is often required after translation.</li>
</ul>

<h2>Common Hive ➝ Trino Issues</h2>

<h3>1. SerDe Incompatibility</h3>
<p>Hive supports a wide range of SerDes that are not available in Trino.</p>

<p><strong>Examples:</strong></p>
<ul>
  <li><code>org.apache.hadoop.hive.serde2.OpenCSVSerde</code> – ❌ <em>Not supported in Trino</em></li>
  <li><code>org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe</code> – ❌ <em>Trino does not support Hive SerDes at all</em></li>
  <li><code>STORED AS TEXTFILE</code> or <code>SEQUENCEFILE</code> – ❌ <em>Not recognized in Trino</em></li>
</ul>

<p><strong>Fix:</strong> Use Trino-compatible table definitions, typically without SerDes.</p>

<h3>2. Function Differences</h3>
<p>Function names and behavior often differ between Hive and Trino.</p>

<p><strong>Examples:</strong></p>
<ul>
  <li>
    <code>from_unixtime(unix_timestamp())</code> – Hive style.
    <br>➡ Use <code>from_unixtime(current_timestamp)</code> in Trino or <code>current_timestamp</code> directly.
  </li>
  <li>
    <code>concat_ws(',', col1, col2)</code> – Supported in Hive.
    <br>➡ In Trino: <code>concat(col1, ',', col2)</code> or use <code>array_join</code> if working with arrays.
  </li>
  <li>
    <code>collect_set()</code> – Hive aggregation to remove duplicates.
    <br>➡ Trino: use <code>array_distinct(array_agg(...))</code>
  </li>
  <li>
    <code>regexp_extract(col, pattern, group)</code> – behaves differently.
    <br>➡ Trino's version returns the match directly, not a list of groups.
  </li>
</ul>

<h3>3. CTAS & Storage Syntax</h3>
<p>Hive:</p>
<code>
  CREATE TABLE my_table STORED AS PARQUET LOCATION '/user/hive/warehouse/';
</code>
<p>Trino:</p>
<code>
  CREATE TABLE hive.schema.my_table WITH (format = 'PARQUET', location = 's3://...') AS SELECT ...
</code>

<p><strong>Fix:</strong> Use Trino’s <code>WITH</code> clause for table properties, not Hive-style storage clauses.</p>

<h2>Tips for Better Results</h2>
<ul>
  <li>Simplify queries before translating</li>
  <li>Use ANSI-compliant syntax when possible</li>
  <li>Know your source and target dialects' limitations</li>
  <li>Try both engines if you're unsure</li>
</ul>

<p> For further help, check out the
  <a :href="TRINO_DOCS_DNS+'/docs/current/appendix/from-hive.html'" target="_blank" rel="noopener">
    Trino documentation about migrating from Hive
  </a>.</p>

  </div>
</template>

<script>
import { fetchRuntimeConfig } from '../utils/config'

export default {
  data () {
    return {
      TRINO_DOCS_DNS: 'https://trino.io'
    }
  },
  async created () {
    const config = await fetchRuntimeConfig()
    if (config.TRINO_DOCS_DNS) {
      this.TRINO_DOCS_DNS = config.TRINO_DOCS_DNS
    }
  }
}
</script>

<style>
 .help-text {
      margin: 2rem;
      line-height: 1.6;
     display: flex;
     flex-flow:column;
     justify-content: center;
     align-content: center;
     align-items: center;
    }
    .help-text>*{
        text-align: center;
        max-width: 60vw;
    }
    h1, h2 {
      color: #333;
    }
    code {
      background-color: #f4f4f4;
      padding: 2px 4px;
      border-radius: 4px;
      font-family: Consolas, monospace;
    }
    .warning {
      background-color: #fff3cd;
      border-left: 5px solid #ffc107;
      padding: 1rem;
      margin-bottom: 1.5rem;
    }
    ul{
        list-style: none;
    }
</style>
