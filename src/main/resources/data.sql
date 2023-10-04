DROP TABLE IF EXISTS alerts;

CREATE TABLE ALERT  (
 id VARCHAR(50) PRIMARY KEY,
alert_id varchar(255) NOT NULL,
service_id varchar(255) NOT NULL,
service_name varchar(255) NOT NULL,
model varchar(255) NOT NULL,
alert_type varchar(255) NOT NULL,
alert_ts varchar(255) NOT NULL,
severity varchar(255)NOT NULL,
team_slack varchar(255) NOT NULL
);

INSERT INTO alert (id, alert_id, service_id, service_name, model, alert_type, alert_ts, severity, team_slack) VALUES
('0001', 'b950482e9911ec7e41f7ca5e5d9a424f', 'my_test_service_id', 'my_test_service', 'my_test_model','anomaly', '1695644160', 'warning', 'slack_ch'),
('0002', 'b950482e9911ec7e41f7ca5e5d9r324f', 'my_test_service_id', 'my_test_service', 'my_test_model1','anomaly', '1695644150', 'warning', 'slack_ch3'),
('0003', 'b95048r2w911ec7e41f7ca5e5d9a424f', 'my_test_service_id', 'my_test_service', 'my_test_model','anomaly', '1695644132', 'error', 'slack_ch2');



