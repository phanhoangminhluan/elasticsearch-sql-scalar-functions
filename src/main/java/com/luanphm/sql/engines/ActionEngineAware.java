package com.luanphm.sql.engines;

import com.luanphm.sql.enums.ActionEngine;
import com.luanphm.sql.enums.DatabaseEngine;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.29 00:02
 */
public interface ActionEngineAware {

    ActionEngine getActionEngine();

}
