package com.storey247.applicationinsightshelpers;

import com.microsoft.applicationinsights.TelemetryClient;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TelemetryClientHelperTest {
    @Mock
    private TelemetryClient mockTelemetryClient;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getTelemetryClient_returns_telemetryclient() {
        TelemetryClientHelper helper = new TelemetryClientHelper(mockTelemetryClient);
        assertThat(helper.getTelemetryClient()).isSameAs(mockTelemetryClient);
    }

}