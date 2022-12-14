package com.bloxbean.cardano.yacicli.commands.localcluster.api;

import co.nstant.in.cbor.model.Array;
import co.nstant.in.cbor.model.DataItem;
import com.bloxbean.cardano.client.api.model.ProtocolParams;
import com.bloxbean.cardano.client.backend.model.EpochContent;
import com.bloxbean.cardano.yaci.core.helpers.LocalStateQueryClient;
import com.bloxbean.cardano.yaci.core.model.Era;
import com.bloxbean.cardano.yaci.core.protocol.localstate.queries.CurrentProtocolParamQueryResult;
import com.bloxbean.cardano.yaci.core.protocol.localstate.queries.CurrentProtocolParamsQuery;
import com.bloxbean.cardano.yaci.core.protocol.localstate.queries.EpochNoQuery;
import com.bloxbean.cardano.yaci.core.protocol.localstate.queries.EpochNoQueryResult;
import com.bloxbean.cardano.yaci.core.util.CborSerializationUtil;
import com.bloxbean.cardano.yaci.core.util.HexUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/local-cluster/api/epochs")
public class EpochService {

    private LocalQueryClientUtil localQueryClientUtil;

    public EpochService(LocalQueryClientUtil localQueryClientUtil) {
        this.localQueryClientUtil = localQueryClientUtil;
    }

    @GetMapping("latest")
    public Mono<EpochContent> getLatestEpoch() {
        try {
            LocalStateQueryClient localStateQueryClient = localQueryClientUtil.getLocalQueryClient();
            localStateQueryClient.start();
            Mono<EpochNoQueryResult> mono = localStateQueryClient.executeQuery(new EpochNoQuery(Era.Alonzo));

            return mono.map(epochNoQueryResult -> EpochContent.builder().epoch(Integer.valueOf((int) epochNoQueryResult.getEpochNo())).build())
                    .doOnTerminate(() -> localStateQueryClient.shutdown());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Get current protocol parameters. The {number} path variable is ignored. So any value can be passed.")
    @GetMapping("parameters")
    Mono<ProtocolParams> getProtocolParameters() {
        try {
            LocalStateQueryClient localStateQueryClient = localQueryClientUtil.getLocalQueryClient();
            localStateQueryClient.start();
            Mono<CurrentProtocolParamQueryResult> mono = localStateQueryClient.executeQuery(new CurrentProtocolParamsQuery(Era.Alonzo));

            return mono.map(currentProtocolParameters -> {
                        ProtocolParams protocolParams = new ProtocolParams();
                        protocolParams.setMinFeeA(currentProtocolParameters.getProtocolParams().getMinFeeA());
                        protocolParams.setMinFeeB(currentProtocolParameters.getProtocolParams().getMinFeeB());
                        protocolParams.setMaxBlockSize(currentProtocolParameters.getProtocolParams().getMaxBlockSize());
                        protocolParams.setMaxTxSize(currentProtocolParameters.getProtocolParams().getMaxTxSize());
                        protocolParams.setMaxBlockHeaderSize(currentProtocolParameters.getProtocolParams().getMaxBlockHeaderSize());
                        protocolParams.setKeyDeposit(String.valueOf(currentProtocolParameters.getProtocolParams().getKeyDeposit()));
                        protocolParams.setPoolDeposit(String.valueOf(currentProtocolParameters.getProtocolParams().getPoolDeposit()));
                        protocolParams.setEMax(currentProtocolParameters.getProtocolParams().getMaxEpoch());
                        protocolParams.setNOpt(currentProtocolParameters.getProtocolParams().getNOpt());
                        protocolParams.setA0(currentProtocolParameters.getProtocolParams().getPoolPledgeInfluence());
                        protocolParams.setRho(currentProtocolParameters.getProtocolParams().getExpansionRate());
                        protocolParams.setTau(currentProtocolParameters.getProtocolParams().getTreasuryGrowthRate());
                        protocolParams.setDecentralisationParam(currentProtocolParameters.getProtocolParams().getDecentralisationParam()); //Deprecated. Not there
                        protocolParams.setExtraEntropy(currentProtocolParameters.getProtocolParams().getExtraEntropy());
                        protocolParams.setProtocolMajorVer(currentProtocolParameters.getProtocolParams().getProtocolMajorVer());
                        protocolParams.setProtocolMinorVer(currentProtocolParameters.getProtocolParams().getProtocolMinorVer());
                        protocolParams.setMinUtxo(String.valueOf(currentProtocolParameters.getProtocolParams().getMinUtxo()));
                        protocolParams.setMinPoolCost(String.valueOf(currentProtocolParameters.getProtocolParams().getMinPoolCost()));

                        Map<String, Long> v1Costs = getCosts(currentProtocolParameters, Integer.valueOf(0));
                        Map<String, Long> v2Costs = getCosts(currentProtocolParameters, Integer.valueOf(1));
                        protocolParams.setCostModels(new HashMap<>());
                        protocolParams.getCostModels().put("PlutusV1", v1Costs);
                        protocolParams.getCostModels().put("PlutusV2", v2Costs);

                        protocolParams.setPriceMem(currentProtocolParameters.getProtocolParams().getPriceMem());
                        protocolParams.setPriceStep(currentProtocolParameters.getProtocolParams().getPriceStep());
                        protocolParams.setMaxTxExMem(String.valueOf(currentProtocolParameters.getProtocolParams().getMaxTxExMem()));
                        protocolParams.setMaxTxExSteps(String.valueOf(currentProtocolParameters.getProtocolParams().getMaxTxExSteps()));
                        protocolParams.setMaxBlockExMem(String.valueOf(currentProtocolParameters.getProtocolParams().getMaxBlockExMem()));
                        protocolParams.setMaxBlockExSteps(String.valueOf(currentProtocolParameters.getProtocolParams().getMaxBlockExSteps()));
                        protocolParams.setMaxValSize(String.valueOf(currentProtocolParameters.getProtocolParams().getMaxValSize()));
                        protocolParams.setCollateralPercent(BigDecimal.valueOf(currentProtocolParameters.getProtocolParams().getCollateralPercent()));
                        protocolParams.setMaxCollateralInputs(currentProtocolParameters.getProtocolParams().getMaxCollateralInputs());
                        protocolParams.setCoinsPerUtxoSize(String.valueOf(currentProtocolParameters.getProtocolParams().getAdaPerUtxoByte()));
                        return protocolParams;
                    })
                    .doOnTerminate(() -> localStateQueryClient.shutdown());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Long> getCosts(CurrentProtocolParamQueryResult currentProtocolParameters, Integer key) {
        Map<Integer, String> costModels = currentProtocolParameters.getProtocolParams().getCostModels();
        String plutusV1CostModel = costModels.get(key);
        Array plutusV1CostModelArray = (Array) CborSerializationUtil.deserializeOne(HexUtil.decodeHexString(plutusV1CostModel));

        Map<String, Long> costs = new HashMap<>();
        int i = 0;
        for (DataItem costDI : plutusV1CostModelArray.getDataItems()) {
            costs.put(String.valueOf(i++), CborSerializationUtil.toLong(costDI));
        }

        return costs;
    }

}
