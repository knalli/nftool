INSERT INTO table_rows_i18n \(id, locale, table_row, content\) VALUES \(\d+, '(\w{2})_\w{2}', (\d+), ('.*')\);
->
INSERT INTO localized_label (locale, attribute_name, entity_uri, content) VALUES ( '$1', 'content', 'Exercise:1/Task:2/TaskTable:9/TableRow:$2', $3);