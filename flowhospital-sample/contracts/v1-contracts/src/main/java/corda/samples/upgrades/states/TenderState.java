package corda.samples.upgrades.states;

import com.google.common.collect.ImmutableList;
import corda.samples.upgrades.contracts.TenderContract;
import corda.samples.upgrades.schema.TenderSchemaV1;
import net.corda.core.contracts.BelongsToContract;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;
import net.corda.core.schemas.MappedSchema;
import net.corda.core.schemas.PersistentState;
import net.corda.core.schemas.QueryableState;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@BelongsToContract(TenderContract.class)
public class TenderState implements QueryableState {

    private Party tenderingOrganisation;
    private String tenderName;
    private Integer tenderAmount;

    public TenderState(Party tenderingOrganisation, String tenderName, Integer tenderAmount) {
        this.tenderingOrganisation = tenderingOrganisation;
        this.tenderName = tenderName;
        this.tenderAmount = tenderAmount;
    }

    public Party getTenderingOrganisation() {
        return tenderingOrganisation;
    }

    public String getTenderName() {
        return tenderName;
    }

    public Integer getTenderAmount() {
        return tenderAmount;
    }

    @NotNull
    @Override
    public List<AbstractParty> getParticipants() {
        return ImmutableList.of(tenderingOrganisation);
    }

    @NotNull
    @Override
    public PersistentState generateMappedObject(@NotNull MappedSchema schema) {
        return new TenderSchemaV1.PersistentTender(tenderingOrganisation, tenderName, tenderAmount);
    }

    @NotNull
    @Override
    public Iterable<MappedSchema> supportedSchemas() {
        return ImmutableList.of(new TenderSchemaV1());
    }
}
