package com.launchersoft.rmm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author Launchersoft
 * Device DTO
 */
@Data
@Builder
@AllArgsConstructor
public class DeviceDTO {

        private long id;
        private String systemName;
        private String type;

}
